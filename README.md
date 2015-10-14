## Introduction
RxFlux is small light-weight framework that makes it easy to follow the [Flux pattern](https://facebook.github.io/flux/docs/overview.html) with RxJava functionalities.

In order to understand more how to use Flux pattern in Android [@lgvalle](https://github.com/lgvalle) created a nice [example and explanation](https://github.com/lgvalle/android-flux-todo-app). I would recommend to read it before continuing.

## Usage

Gradle:
```
dependencies {
  compile 'com.hardsoftstudio:rxflux:0.1.2'
}
```

RxFlux contains as dependency RxAndroid and the Android support v4 in order to get the benefits of classes like ArrayMap, Pair, etc...

IMPORTANT in order to follow the instructions is highly recommended to look at the example provided in the repo.

The main class is the RxFlux, is the responsible to handle activity lifecycle in order to notify and register stores and views. 

First thing you must do is in init RxFlux in your Application instance
```java
public void onCreate() {
    super.onCreate();
    rxFlux = RxFlux.init();
}
``` 

This will make RxFlux register into the activity lifecycle and init the internal classes.

From now on we will follow the image below to understand each step
![Flux architecture](https://raw.githubusercontent.com/lgvalle/lgvalle.github.io/master/public/images/flux-graph-complete.png)

### View
Each activity of your app must implements RxViewDispatch. This interface defines the methods needed for each view (on Android it applies for our Activity). 

RxFlux will notify each activity that implements RxViewDispatch during the creation of it and will call ```onRxStoresRegister()``` in this method we must call the instance of the needed stores and register them.

For example: (see stores section for more)
```java
@Override
  public void onRxStoresRegister() {
    repositoriesStore = RepositoriesStore.get(mDispatcher);
    repositoriesStore.register();
}
```

RxFlux will register store change for each activity during the ```onResume``` method and then call ```onRxViewRegistered()``` so we can register the activity fragments or custom views. (Todo see how to register)

```onRxStoreChanged(RxStoreChange change)``` here we will get notified by any store change that occurs in our app. For that reasons we must filter by storeId to identify which store has change.

```java
  @Override
  public void onRxStoreChanged(RxStoreChange change) {
    switch (change.getStoreId()) {
      case RepositoriesStore.ID:
        // Identify which type of action was and update view according to that
        break;
    }
  }
```
Finally we should implement the ```onRxError(RxError error)``` it will notify when and error has occurred, so we can get the action used for it and the throwable instance, and for example retry the action. 

### RxActionCreator

Next step is to create our ActionsCreator in order to do so, we create a new class and extends the RxActionCreator.
Basically this abstract class will provide us with some extra functionality to handle the creation of the actions and the request.

The recommended way is to create and interface that defines the type of Actions and the methods. Following the example we can see we have the following interface.

```java
public interface Actions {

  String GET_PUBLIC_REPOS = "get_public_repos";
  String GET_USER = "get_user";

  void getPublicRepositories();

  void getUserDetails(String userId);
}
```

Then our ActionsCreator will look like:
```java
public class GitHubActionCreator extends RxActionCreator implements Actions { 
   ...
}
```

The construction will need the instance of the Dispatcher and SubscriptionManager that you can get from RxFlux.
The SubscriptionManager is an important class that handles subscriptions by tag, the tag is the hashCode of the RxAction that we create, this will help us to avoid duplicated action running in the same time and make sure we unsubscribe from the Subscriptions whenever the app close. Also allows us a simple mechanism to cancel request.

The RxActionCreator offer the following methods:

* ```boolean hasRxAction(RxAction rxAction)``` Checks if there is already and action running.
* ```void removeRxAction(RxAction rxAction)``` Remove the specific action if exists
* ```RxAction newRxAction(@NonNull String actionId, @NonNull Object... data)``` Creates a new
RxAction with the specific actionId and the data. This object must be a key-value pair i.e newRxAction(GET_USER, Keys.ID, userId);
* ```postRxAction(RxAction action)``` Will send the action into the dispatcher
* ```postError(RxAction action, Throwable throwable)``` Creates a new RxError instance that
contains the RxAction created and the throwable instance then will post the error into the dispatcher so the store can react to it.

### RxAction

This class represent an action of RxFlux, it contains the type and a key-value pair with the data. It's use to trigger events in the dispatcher.

### RxStore 

This class represents the Model + Logic of the app. In order to simplify the creation of it, every Store that we want to create must extends the abstract class. 

The best way to use them is in a DI system or a singleton pattern. Once register to the dispatcher the store will get all the RxActions from the dispatcher, then we can filter by type and store the data, update our model or do the logic of our app, once this is done we should call postChange() to notify the View.

```java
public class RepositoriesStore extends RxStore implements RepositoriesStoreInterface {

  public static final String ID = "RepositoriesStore";
  private static RepositoriesStore instance;
  private ArrayList<GitHubRepo> gitHubRepos;

  private RepositoriesStore(Dispatcher dispatcher) {
    super(dispatcher);
  }

  public static synchronized RepositoriesStore get(Dispatcher dispatcher) {
    if (instance == null) instance = new RepositoriesStore(dispatcher);
    return instance;
  }

  @Override
  public void onRxAction(RxAction action) {
    switch (action.getType()) {
      case Actions.GET_PUBLIC_REPOS:
        this.gitHubRepos = (ArrayList<GitHubRepo>) action.getData().get(Keys.PUBLIC_REPOS);
        break;
      default: // IMPORTANT if we don't modify the store just ignore
        return;
    }
    postChange(new RxStoreChange(ID, action));
  }

  @Override
  public ArrayList<GitHubRepo> getRepositories() {
    return gitHubRepos == null ? new ArrayList<GitHubRepo>() : gitHubRepos;
  }
}
```

Finally each store should provide an interface so the View can request the model. As you can see in the example we provide the getRepositories().

## Final notes

RxFlux just provide an easy way to implement Flux pattern, it requires manual integration and the developer must follow the Flux flow in order to work properly.

**Pros:**
* Clean architecture.
* Simple flow.
* Logic abstraction and well defined.
* Simple to test.
* Modular.
* Reactive.
* Easy to add Hooks, like tracking tools. 

**Contras:**
* One way, restrictive flow.
* Different pattern, needs to learn a bit before using it.

## Author
This framework was developed by @skimarxall. It is being used in a private production environment. The idea of this framework came after reading the article of @lgvalle. 

The contributions are totally welcome, but all the PR must follow the coding rules defined [here](https://github.com/square/java-code-styles/blob/master/configs/codestyles/SquareAndroid.xml).


License
=======

    Copyright 2015 Marcel Pintó Biescas

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.