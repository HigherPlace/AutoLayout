# AutoLayout

自适应布局，参照鸿洋的AndroidAutoLayout，修复了旋屏幕适配会有问题的情况

##Use

##### gradle引用
1. 添加JitPack仓库到build文件中
````
    allprojects {
	        repositories {
		        ...
		        maven { url 'https://jitpack.io' }
	        }
    }
````

2. Add the dependency
````
    dependencies {
	        compile 'com.github.ashima0512:OrangeRetrofitDemo:v1.0.0'
    }
````