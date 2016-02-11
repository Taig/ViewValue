# ViewValue
Android View API helper

## Dependencies

**Support for basic widgets** (such as `TextView`)

````scala
"io.taig.android.viewvalue" %% "core" % "1.0.0"
````

**Support for design support library widgets** (such as `TextInputLayout`)

````scala
"io.taig.android.viewvalue" %% "design" % "1.0.0"
````

## Usage

````scala
import android.widget.TextView
import io.taig.android.viewvalue.syntax.all._

val tv: TextView = ???

val c1 = tv.value[String]
val c2 = tv.value[Option[String]]

tv.value = "foo"
tv.value = Some( "foo" )
tv.value = None
````

## Supported widgets

### Core

 - `CompoundButton` → `Boolean` (Checked state)
 - `RadioGroup` → `Int`, `Option[Int]` (Selected item id)
 - `TextView` → `String`, `Option[String]` (Text value)

### Design

 - `TextInputLayout` → `String`, `Option[String]` (Text value)
