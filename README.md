# learnMotionEventDistribution
+ [Android事件分发机制，大表哥带你慢慢深入](https://www.jianshu.com/p/fc0590afb1bf)（图解很赞）
+ [Android View 事件分发机制源码解析](https://blog.csdn.net/lmj623565791/article/details/38960443)（鸿洋写的）
+ [Android ViewGroup事件分发机制](https://blog.csdn.net/lmj623565791/article/details/39102591)（鸿洋写的）
+ [Android View事件分发机制源码解析](https://www.jianshu.com/p/1bb072e0d35a)（上篇结合了新源码）
+ 《Android艺术开发探索》第三章（不解释）


## 总结：
#### 1.事件分发过程由三个方法协助完成
+ dispatchTouchEvent()       //分配
+ onInterceptTouchEvent()  //拦截
+ onTouchEvent()                //处理

````
    //三者的关系（伪代码）
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean consume = false;
        if(onInterceptTouchEvent(ev)){
            consume = onTouchEvent(ev);
        }else {
            consume = child.dispatchTouchEvent(ev);
        }
        return consume;
    }
````

#### 2.事件分发顺序：
Activity（Window） -> ViewGroup -> View

#### 3.拦截事件
在事件分发三大类（Activity、ViewGroup、View）中，Activity和View不会去拦截事件（也就是不能重写onInterceptTouchEvent()方法）

#### 4.事件分发路线呈U型
groupA  包 groupB 包 viewB，全部super调用不拦截，日志如下（看图解那篇）
````
--------------dispatchTouchEvent--------------EventActivity
--------------dispatchTouchEvent--------------groupA
--------------onInterceptTouchEvent--------------groupA
--------------dispatchTouchEvent--------------groupB
--------------onInterceptTouchEvent--------------groupB
--------------dispatchTouchEvent--------------viewB
--------------onTouchEvent--------------viewB
--------------onTouchEvent--------------groupB
--------------onTouchEvent--------------groupA
--------------onTouchEvent--------------EventActivity
````
#### 5.理解dispatchTouchEvent（是否分发或传递事件）
>+  **返回false**：**不再向下分发**，无论是ViewGroup还是View，都会从该View的上一级的onTouchEvent事件向上传递
（ 注意：当一个View不在向下分发时（dispatchTouchEvent），不会执行自己的onTouchEvent（）的，拦截时（onInterceptTouchEvent） 会执行onTouchEvent（））
>
>+ **返回true**：表示**事件直接被消费**，这个事件也就停止分发且不会逆向向上传递，直接结束了。
>
>+ **返回super**：事件将会继续向下分发，直到事件被消费为止。

#### 6.理解onInterceptTouchEvent（是否拦截该事件，默认不拦截）
>+ **返回false**（super默认false）:此次**不拦截**，事件将会正常向下分发，分发至下级的dispatchTouchEvent方法 再次判断是否分发事件。
> 
>+ **返回true**:**拦截**后续事件，会执行该View的`onTouchEvent（）`方法然后停止向下分发转而通过`onTouchEvent（）`向上传递，直到最终被消费

#### 7. 理解onTouchEvent（是否消费掉此次事件）
首先，理解onTouchEvent方法的触发满足的条件，在正常流程下，我们提到过整个流程呈U形，U形的转折点就是从Activity开始事件向下分发到最后一个View或者ViewGroup的onTouchEvent（）方法。
>+ **返回true**：立即消费掉事件，事件将不会向上传递，事件到此终止。
>+ **返回false/super**：不消费掉此次事件，事件将会层层向上传递，直到被消费。

#### 8.理解事件消费
简而言之，`onTouchEvent`返回`true`就表示此次事件被消费掉

````
        findViewById(R.id.A).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
````
如果view设置了触摸事件，onTouch实现中
>+ **返回true**：立即消费掉事件，事件将不会向上传递，事件到此终止。
>+ **返回false/super**：不消费掉此次事件，事件将会层层向上传递，直到被消费。


![事件分发U形图](https://upload-images.jianshu.io/upload_images/1767630-25c6e07c2d1439a0.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![Demo事件分发U形图](https://upload-images.jianshu.io/upload_images/1767630-307e7cacec537692.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

# 大总结
> 1.`dispatchTouchEvent` 和 `onTouchEvent` 一旦`return true`,事件就停止传递了（到达终点）（没有谁能再收到这个事件）。看上图中只要`return true`事件就没再继续传下去了，对于`return true`我们经常说事件被消费了，消费了的意思就是事件走到这里就是终点，不会往下传，没有谁能再收到这个事件了。
>
>2.`dispatchTouchEvent `和 `onTouchEvent`方法在`return false`的时候，事件都回传给父控件的`onTouchEvent`处理。
> 
>> + 对于`dispatchTouchEvent `返回` false `的含义应该是：事件停止往子`View`传递和分发同时开始往父控件回溯（父控件的`onTouchEvent`开始从下往上回传直到某个`onTouchEvent return true`），事件分发机制就像递归，`return false` 的意义就是递归停止然后开始回溯。
>>+ 对于`onTouchEvent return false `就比较简单了，它就是不消费事件，并让事件继续往父控件的方向从下往上流动。
> 
> 3.`onInterceptTouchEvent `的作用
> `onInterceptTouchEvent`方法中` return true`就会交给自己的`onTouchEvent`的处理，如果不拦截就是继续往子控件往下传。默认是不会去拦截的，因为子`View`也需要这个事件，所以`onInterceptTouchEvent`拦截器`return super.onInterceptTouchEvent()`和`return false`是一样的，是不会拦截的，事件会继续往子`View`的`dispatchTouchEvent`传递。

#### 鸿洋两篇总结

1、View的事件转发流程是：
> View.**dispatchEvent**->View.**setOnTouchListener**->View.**onTouchEvent**
在`dispatchTouchEvent`中会进行`OnTouchListener`的判断，如果`OnTouchListener`不为`null`且返回`true`，则表示事件被消费，`onTouchEvent`不会被执行；否则执行`onTouchEvent`。

2、ViewGroup
>+ 如果`ViewGroup`找到了能够处理该事件的`View`，则直接交给子`View`处理，自己的`onTouchEvent`不会被触发；
>
>+ 可以通过复写`onInterceptTouchEvent(ev)`方法，拦截子`View`的事件（即return true），把事件交给自己处理，则会执行自己对应的`onTouchEvent`方法
>
>+ 子`View`可以通过调用`getParent().requestDisallowInterceptTouchEvent(true)`;  阻止`ViewGroup`对其`MOVE`或者`UP`事件进行拦截；


[简书地址](https://www.jianshu.com/p/ab13658b1fd2)
