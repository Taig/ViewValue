# ViewValue
Android View API helper

## Dependencies

**Support for basic widgets** (such as `TextView`)

````scala
"io.taig.android" %% "viewvalue-core" % "1.2.6"
````

**Support for design library widgets** (such as `TextInputLayout`)

````scala
"io.taig.android" %% "viewvalue-design" % "1.2.6"
````

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