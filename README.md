## Introduction
RxFlux is small light-weight framework that makes it easy to follow the [Flux pattern](https://facebook.github.io/flux/docs/overview.html) with RxJava functionalities.

## Why?

RxFlux just provide an easy way to implement Flux pattern, it requires manual integration and the developer must follow the Flux flow in order to work properly.

**Pros:**
* Clean architecture.
* Simple flow.
* Logic abstraction
* Simple to test.
* Modular.
* Reactive.
* Easy to add Hooks, like tracking tools. 

**Contras:**
* One way, restrictive flow.
* Different pattern, needs to learn a bit before using it.

For more information please read my arctile at medium. [Why RxFlux?](https://medium.com/swlh/why-rxflux-5b687f062709#.ir5uztnkz)

## Getting started

RxFlux is a framework, that means that requires some work, is not just a library. Please follow the wiki to integrate RxFlux in your next project. [RxFlux Wiki](https://github.com/skimarxall/RxFlux/wiki)

For a real case example read my post at medium [RxFlux Android Architecture](https://medium.com/swlh/rxflux-android-architecture-94f77c857aa2#.u0bwu76i9)

## Gradle:
```
dependencies {
  compile 'com.hardsoftstudio:rxflux:0.3.1'
}
```


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
