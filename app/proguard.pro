# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# Flurry
-keep class com.flurry.** { *; }
-dontwarn com.flurry.**

-dontwarn com.ihs.interstitialads.**
-dontwarn com.ihs.affiliateads.HeaderAds.AffiliateHeader**
-dontwarn com.ihs.adreport.**
-dontwarn com.acb.**

-dontwarn com.android.installreferrer

# Remove logs
-assumenosideeffects class com.ihs.commons.utils.HSLog {
    public static void v(...);
    public static void d(...);
    public static void i(...);
    public static void w(...);
    public static void e(...);
    public static void pt(...);
}

# ==== Required by dependencies ====
-keep public class com.google.android.gms.analytics.** { public *; }
-dontwarn com.google.android.gms.analytics.**

# Workaround for building project with Google Play Services
-keep class com.google.android.gms.iid.zzd { *; }
-keep class android.support.v4.content.ContextCompat { *; }

-keepattributes EnclosingMethod

# Tapjoy
-keep class com.tapjoy.** { *; }
-dontwarn com.tapjoy.**

-dontwarn com.google.firebase.**
-dontwarn com.amazon.**
-dontwarn com.appsflyer.FirebaseInstanceIdListener**

-dontwarn com.ihs.interstitialads.**
-dontwarn com.ihs.affiliateads.HeaderAds.AffiliateHeader**
-dontwarn com.ihs.adreport.**
-dontwarn com.acb.**

# Required by Gradle Retrolambda plugin
-dontwarn java.lang.invoke.*

# Fabric Crashlytics
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable

-keep class online.sudoku.puzzle.solver.easy.free.db.entity.** { *; }

-keep class com.ihs.app.push.impl.FcmFirebaseMessagingService { *; }