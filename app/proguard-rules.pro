# ButterKnife rules
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

# Retrofit rules
-dontwarn retrofit.**
-keep class retrofit.** { *; }
-keepattributes Signature
-keepattributes Exceptions

# OkHttp rules
-dontwarn okio.**
-dontwarn com.squareup.okhttp.**

# Otto rules
-keepclassmembers class ** {
    @com.squareup.otto.Subscribe public *;
    @com.squareup.otto.Produce public *;
}

# RxJava rules
# RxAndroid will soon ship with rules so this may not be needed in the future
# https://github.com/ReactiveX/RxAndroid/issues/219
-dontwarn sun.misc.Unsafe
-keep class rx.internal.util.unsafe.** { *; }

# EasyAdapter rules
-keepclassmembers class * extends uk.co.ribot.easyadapter.ItemViewHolder {
    public <init>(...);
 }

# Gson rules
-keepattributes Signature
-keep class sun.misc.Unsafe { *; }

# TODO change to match your package model
# Keep non static or private fields of models so Gson can find their names
-keepclassmembers class com.hardsoftstudio.rxflux.sample.data.model.** {
    !static !private <fields>;
}
# TODO change to match your Retrofit services (only if using inner models withing the service)
# Some models used by gson are inner classes inside the retrofit service
-keepclassmembers class com.hardsoftstudio.rxflux.sample.data.remote.XService$** {
    !static !private <fields>;
}

# Produces useful obfuscated stack traces
# http://proguard.sourceforge.net/manual/examples.html#stacktrace
-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable
