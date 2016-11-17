# Changelog

## 1.5.0

_2016-11-17_

 * Move everything into one module!
   This enforces the `support-design` library, but avoids implicit ambiguities and does not require additional imports.

## 1.4.1

_2016-11-16_

 * Fix root module `implicits` definition

## 1.4.0

_2016-11-16_

 * Upgrade to Android support library 25.0.1
 * Provide implicits from root project
 * Split core module into core and base modules
 * Upgrade to sbt-android 1.7.1
 * Upgrade to sbt-scalariform 1.7.1
 * Upgrade to sbt 0.13.13

## 1.3.1

_2016-09-02_

 * Upgrade to sbt-android 1.6.16
 * Fix `TextView.error` behavior with `TextInputLayout`

## 1.3.0

_2016-08-26_

 * Upgrade to Android support library 24.2.0
 * Upgrade sbt to 0.13.12
 * Upgrade to sbt-android 1.6.14
 * Increase minSdkVersion from 7 to 9
 * `design.implicits` no longer extends `core.implicits`
 * Set platform target to 24
 * Use default location for R classes

## 1.2.5

 * Upgraded to support library 24.0.0

## 1.2.4

 * Upgraded to support library 23.4.0

## 1.2.3

 * Upgraded to support library 23.3.0

## 1.2.2

 * Upgraded to support library 23.2.1 and Scala 2.11.8

## 1.2.1

 * Upgraded to support library 23.2.0

## 1.2.0

 * Support for error message injections/extractions

## 1.1.1

 * Enforce Java 7 compilation

## 1.1.0

 * Added `ImageView` type classes
 * Added resource id Injections for `TextView` and `TextInputLayout`

## 1.0.0

 * Initial release