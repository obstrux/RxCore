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
    compile 'com.github.alphaKnife:RxCore:0.1.1'
```