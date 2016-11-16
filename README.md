# ViewValue

> Android View API helper

## Dependencies

Load modules individually …

```scala
"io.taig.android" %% "viewvalue-core" % "1.4.0"

// Instances for Android SDK widgets
"io.taig.android" %% "viewvalue-base" % "1.4.0"

// Instances for Android design library widgets
"io.taig.android" %% "viewvalue-design" % "1.4.0"
```

… or load all modules and get one `implicits` import for all modules.

```scala
"io.taig.android" %% "viewvalue" % "1.4.0"
```

## Usage

````scala
import android.widget.TextView
import io.taig.android.viewvalue.implicits._

val tv: TextView = new TextView( context )

val c1 = tv.value[String]
val c2 = tv.value[Option[String]]

tv.value = "foo"
tv.value = Some( "foo" )
tv.value = None

tv.error = Some( "my error message" )
tv.error = "my error message"
tv.error = None
````

## Supported widgets

### Core

#### Value

| Widget | Injections | Extractions | Description |
| --- | --- | --- | --- |
| `CompoundButton` | `Boolean` | `Boolean` | Checked state |
| `ImageView` | `Drawable`, `Bitmap`, `Int` (resource), `Uri` | `Drawable` | Image drawable (foreground) |
| `RadioGroup` | `Int`, `Option[Int]` | `Int`, `Option[Int]` | Selected item id |
| `TextView` | `CharSequence`, `Option[CharSequence]`, `Int` (resource) | `String`, `Option[String]` | Text value |

#### Error

| Widget | Injections | Extractions | Description |
| --- | --- | --- | --- |
| `TextView` | `CharSequence`, `Option[CharSequence]`, `Int` (resource) | `Option[String]` | Error value |

### Design

#### Value

| Widget | Injections | Extractions | Description |
| --- | --- | --- | --- |
| `TextInputLayout` | `CharSequence`, `Option[CharSequence]`, `Int` (resource) | `String`, `Option[String]` | Text value of the inner `EditText` |

#### Error

| Widget | Injections | Extractions | Description |
| --- | --- | --- | --- |
| `TextInputLayout` | `CharSequence`, `Option[CharSequence]`, `Int` (resource) | `Option[String]` | Error value |
| `TextView` | `CharSequence`, `Option[CharSequence]`, `Int` (resource) | `Option[String]` | Error value will be forwarded to parent `TextInputLayout` (if available) |