# ViewValue
Android View API helper

## Dependencies

**Support for basic widgets** (such as `TextView`)

````scala
"io.taig.android.viewvalue" %% "core" % "1.1.1"
````

**Support for design library widgets** (such as `TextInputLayout`)

````scala
"io.taig.android.viewvalue" %% "design" % "1.1.1"
````

## Usage

````scala
import android.widget.TextView
import io.taig.android.viewvalue.syntax.all._

val tv: TextView = new TextView( context )

val c1 = tv.value[String]
val c2 = tv.value[Option[String]]

tv.value = "foo"
tv.value = Some( "foo" )
tv.value = None
````

## Supported widgets

### Core

 - `CompoundButton`  
 **Injection & Extraction** → `Boolean` (Checked state)
 - `ImageView`  
 **Injection & Extraction** → `Drawable`  
 **Injection** → `Bitmap`, `Int`, `Uri`
 - `RadioGroup`  
 **Injection & Extraction** → `Int`, `Option[Int]` (Selected item id)
 - `TextView`  
 **Injection & Extraction** → `String`, `Option[String]` (Text value)  
 **Injection** → `Int` (Resource id)

### Design

 - `TextInputLayout`  
 **Injection & Extraction** → `String`, `Option[String]` (Text value)  
 **Injection** → `Int` (Resource id)