JavaBean的命名最佳实践:
1. javabean属性命名尽量使用常规的驼峰式命名规则
2. 属性名第一个单词尽量避免使用一个字母：如eBook， eMail。
3. boolean属性名避免使用 “is” 开头的名称

---
JavaBean 规范
（1）JavaBean 类必须是一个公共类，并将其访问属性设置为 public  ，如： public class user{......}
（2）JavaBean 类必须有一个空的构造函数：类中必须有一个不带参数的公用构造器

（3）一个javaBean类不应有公共实例变量，类变量都为private  ，如： private int id;
（4）属性应该通过一组存取方法（getXxx 和 setXxx）来访问，一般是IDE(Eclipse、JBuilder) 为属性生成getter/setter 方法

一般JavaBean属性以小写字母开头，驼峰命名格式，相应的 getter/setter 方法是 get/set 接上首字母大写的属性名。例如：属性名为userName，其对应的getter/setter 方法是 getUserName/setUserName。

但是，还有一些特殊情况：

1、如果属性名的第二个字母大写，那么该属性名直接用作 getter/setter 方法中 get/set 的后部分，就是说大小写不变。例如属性名为uName，方法是getuName/setuName。

2、如果前两个字母是大写（一般的专有名词和缩略词都会大写），也是属性名直接用作 getter/setter 方法中 get/set 的后部分。例如属性名为URL，方法是getURL/setURL。

3、如果首字母大写，也是属性名直接用作 getter/setter 方法中 get/set 的后部分。例如属性名为Name，方法是getName/setName，这种是最糟糕的情况，会找不到属性出错，因为默认的属性名是name。

所以在JavaBean命名时应该注意符合以上命名规范。

附下表，看看吧：

|属性名/类型              | getter                             |setter                                      |        
|-----------------------|------------------------------------|--------------------------------------------|
|xcoordinate/Double     | public Double getXcoordinate()     | public void setXcoordinate(Double newValue)|
|xCoordinate/Double     | public Double getxCoordinate()     | public void setxCoordinate(Double newValue)|
|XCoordinate/Double     | public Double getXCoordinate()     | public void setXCoordinate(Double newValue)|
|Xcoordinate/Double     | 不允许                              |    不允许                                    |

