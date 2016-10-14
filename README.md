#### RxCore
----

project file build.gradle
```
	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
```

module file build.gradle

```
    compile 'com.github.alphaknife:rxcore:0.1.1'
```