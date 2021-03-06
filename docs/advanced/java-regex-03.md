## 导读

**正则表达式是什么？有什么用？**
**_正则表达式(Regular Expression)_**是一种文本规则，可以用来**校验**、**查找**、**替换**与规则匹配的文本。

**又爱又恨的正则**

正则表达式是一个强大的文本匹配工具，但是它的规则实在很繁琐，而且理解起来也颇为蛋疼，容易让人望而生畏。

**如何学习正则**

刚接触正则时，我看了一堆正则的语义说明，但是仍然不明所以。后来，我多接触一些正则的应用实例，渐渐有了感觉，再结合语义说明，终有领悟。我觉得正则表达式和武侠修练武功差不多，应该先练招式，再练心法。如果一开始就直接看正则的规则，保证你会懵逼。当你熟悉基本招式（正则基本使用案例）后，也该修炼修炼心法（正则语法）了。真正的高手不能只靠死记硬背那么几招把式。就像张三丰教张无忌太极拳一样，领悟心法，融会贯通，少侠你就可以无招胜有招，成为传说中的绝世高手。

**以上闲话可归纳为一句：学习正则应该从实例去理解规则。**

![欲练神功，必先自宫](http://upload-images.jianshu.io/upload_images/3101171-e6b876043b7ba083.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
打开秘籍：欲练神功，必先自宫！没有蛋，也就不会蛋疼了。

**Java 正则速成秘籍分三篇：**

* **_[Java 正则速成秘籍（一）之招式篇](http://www.cnblogs.com/jingmoxukong/p/6026474.html)_**

展示 Java 对于正则表达式的支持。

* [**_Java 正则速成秘籍（二）之心法篇_**](http://www.cnblogs.com/jingmoxukong/p/6030197.html)

介绍正则表达式的语法规则。

* [**_Java 正则速成秘籍（三）之见招拆招篇_**](http://www.cnblogs.com/jingmoxukong/p/6040572.html)

从实战出发，介绍正则的常用案例。

> 本文是 Java 正则速成秘籍的最后一篇——见招拆招篇。
>
> 在 [Java 正则速成秘籍（一）之招式篇](http://www.cnblogs.com/jingmoxukong/p/6026474.html) 和 [Java 正则速成秘籍（二）之心法篇](http://www.cnblogs.com/jingmoxukong/p/6030197.html)，我们学习了 Java 支持正则功能的 API 以及正则表达式的语法。
>
> 本文则主要展示正则表达式在现实场景的应用。文中不会再提及正则的语法以及 Java 正则相关的 API，如有疑问，可以参考前面两篇文章。
>
> **注：本文展示的案例，已经经过我的充分测试。**如果你有兴趣，可以参考 [**_我的 github 单元测试源码_**](https://github.com/dunwu/JavaSENotes/blob/master/src/test/java/org/zp/notes/javase/regex/RegexUtilTest.java) 。

## 正则应用

虽然本系列洋洋洒洒的大谈特谈正则表达式。但是我还是要在这里建议，如果一个正则表达式没有经过充分测试，还是要谨慎使用。

正则是把双刃剑，它可以为你节省大量的代码行。但是由于它不易阅读，维护起来可是头疼的哦（你需要一个字符一个字符的去理解）。

### 最实用的正则

#### 校验中文

**描述：**校验字符串中只能有中文字符（不包括中文标点符号）。中文字符的 Unicode 编码范围是\u4e00 到 \u9fa5。

如有兴趣，可以参考[**_百度百科-Unicode_**](http://baike.baidu.com/link?url=3xi0vmvCIGKQLJZdn_BYhQ1IDFsoSJMrya6_eOjCBb7A6cRIW-zhZFLC9Yh8wjxU6A_HCfNuP8FBBXU9CN3Wcq) 。

```
^[\u4e00-\u9fa5]+$
```

**匹配：** 春眠不觉晓

**不匹配：**春眠不觉晓，

#### 校验身份证号码

**描述：**身份证为 15 位或 18 位。15 位是第一代身份证。从 1999 年 10 月 1 日起，全国实行公民身份证号码制度，居民身份证编号由原 15 位升至 18 位。

**15 位身份证**

**描述：**由 15 位数字组成。排列顺序从左至右依次为：六位数字地区码；六位数字出生日期；三位顺序号，其中 15 位男为单数，女为双数。

**18 位身份证**

**描述：**由十七位数字本体码和一位数字校验码组成。排列顺序从左至右依次为：六位数字地区码；八位数字出生日期；三位数字顺序码和一位数字校验码（也可能是 X）。

身份证号含义详情请见：[**_百度百科-居民身份证号码_**](http://baike.baidu.com/link?url=5mYlYNE0RsSe2D4tydajtiaR8hAm4pPZ0FHSPuQ05N4f6H-i7qPuw7sY5KfNuiOVJWVWZvU4gf3IY-vIcKdP1CU4Fv-9pKmFQB50qGv_hZT2dkGbkd9--8_saY7omV80vEw9ixVeEwda37fHswfmtyU4QSiBG5s3K5K-JnYr1dqNlPu0f3t008UcLh5-wyID)

**地区码（6 位）**

```
(1[1-5]|2[1-3]|3[1-7]|4[1-3]|5[0-4]|6[1-5])\d{4}
```

**出生日期（8 位）**

注：下面的是 18 位身份证的有效出生日期，如果是 15 位身份证，只要将第一个\d{4}改为\d{2}即可。

```
((\d{4}((0[13578]|1[02])(0[1-9]|[12]\d|3[01])|(0[13456789]|1[012])(0[1-9]|[12]\d|30)|02(0[1-9]|1\d|2[0-8])))|([02468][048]|[13579][26])0229)
```

**15 位有效身份证**

```
^((1[1-5]|2[1-3]|3[1-7]|4[1-3]|5[0-4]|6[1-5])\d{4})((\d{2}((0[13578]|1[02])(0[1-9]|[12]\d|3[01])|(0[13456789]|1[012])(0[1-9]|[12]\d|30)|02(0[1-9]|1\d|2[0-8])))|([02468][048]|[13579][26])0229)(\d{3})$
```

**匹配：**110001700101031

**不匹配：**110001701501031

**18 位有效身份证**

```
^((1[1-5]|2[1-3]|3[1-7]|4[1-3]|5[0-4]|6[1-5])\d{4})((\d{4}((0[13578]|1[02])(0[1-9]|[12]\d|3[01])|(0[13456789]|1[012])(0[1-9]|[12]\d|30)|02(0[1-9]|1\d|2[0-8])))|([02468][048]|[13579][26])0229)(\d{3}(\d|X))$
```

**匹配：**110001199001010310 | 11000019900101015X

**不匹配：**990000199001010310 | 110001199013010310

#### 校验有效用户名、密码

**描述：**长度为 6-18 个字符，允许输入字母、数字、下划线，首字符必须为字母。

```
^[a-zA-Z]\w{5,17}$
```

**匹配：**he_llo@worl.d.com | hel.l-o@wor-ld.museum | h1ello@123.com

**不匹配：**hello@worl_d.com | he&llo@world.co1 | .hello@wor#.co.uk

#### 校验邮箱

**描述：**不允许使用 IP 作为域名，如 : hello@154.145.68.12

`@`符号前的邮箱用户和`.`符号前的域名(domain)必须满足以下条件：

* 字符只能是英文字母、数字、下划线`_`、`.`、`-` ；
* 首字符必须为字母或数字；
* `_`、`.`、`-` 不能连续出现。

域名的根域只能为字母，且至少为两个字符。

```
^[A-Za-z0-9](([_\.\-]?[a-zA-Z0-9]+)*)@([A-Za-z0-9]+)(([\.\-]?[a-zA-Z0-9]+)*)\.([A-Za-z]{2,})$
```

**匹配：**he_llo@worl.d.com | hel.l-o@wor-ld.museum | h1ello@123.com

**不匹配：**hello@worl_d.com | he&llo@world.co1 | .hello@wor#.co.uk

#### 校验 URL

**描述：**校验 URL。支持 http、https、ftp、ftps。

```
^(ht|f)(tp|tps)\://[a-zA-Z0-9\-\.]+\.([a-zA-Z]{2,3})?(/\S*)?$
```

**匹配：**http://google.com/help/me | http://www.google.com/help/me/ | https://www.google.com/help.asp | ftp://www.google.com | ftps://google.org

**不匹配：**http://un/www.google.com/index.asp

#### 校验时间

**描述：**校验时间。时、分、秒必须是有效数字，如果数值不是两位数，十位需要补零。

```
^([0-1][0-9]|[2][0-3]):([0-5][0-9])$
```

**匹配：**00:00:00 | 23:59:59 | 17:06:30

**不匹配：**17:6:30 | 24:16:30

#### 校验日期

**描述：**校验日期。日期满足以下条件：

* 格式 yyyy-MM-dd 或 yyyy-M-d
* 连字符可以没有或是“-”、“/”、“.”之一
* 闰年的二月可以有 29 日；而平年不可以。
* 一、三、五、七、八、十、十二月为 31 日。四、六、九、十一月为 30 日。

```
^(?:(?!0000)[0-9]{4}([-/.]?)(?:(?:0?[1-9]|1[0-2])\1(?:0?[1-9]|1[0-9]|2[0-8])|(?:0?[13-9]|1[0-2])\1(?:29|30)|(?:0?[13578]|1[02])\1(?:31))|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)([-/.]?)0?2\2(?:29))$
```

**匹配：**2016/1/1 | 2016/01/01 | 20160101 | 2016-01-01 | 2016.01.01 | 2000-02-29

**不匹配：**2001-02-29 | 2016/12/32 | 2016/6/31 | 2016/13/1 | 2016/0/1

#### 校验中国手机号码

**描述：**中国手机号码正确格式：11 位数字。

移动有 16 个号段：134、135、136、137、138、139、147、150、151、152、157、158、159、182、187、188。其中 147、157、188 是 3G 号段，其他都是 2G 号段。联通有 7 种号段：130、131、132、155、156、185、186。其中 186 是 3G（WCDMA）号段，其余为 2G 号段。电信有 4 个号段：133、153、180、189。其中 189 是 3G 号段（CDMA2000），133 号段主要用作无线网卡号。总结：13 开头手机号 0-9；15 开头手机号 0-3、5-9；18 开头手机号 0、2、5-9。

此外，中国在国际上的区号为 86，所以手机号开头有+86、86 也是合法的。

以上信息来源于 [**_百度百科-手机号_**](http://baike.baidu.com/link?url=Bia2K_f8rGcakOlP4d9m_-DNSgXU5-0NDP0pPavS0ZbhRHQcUFUTbMERjdO4u7cvkpTJaIDeUXq_EXWnMqXMdSuMQDX3NAbZXAlZYl_V18KATWF7y1EFzUyJ62rf3bAN)

```
^((\+)?86\s*)?((13[0-9])|(15([0-3]|[5-9]))|(18[0,2,5-9]))\d{8}$
```

**匹配：**+86 18012345678 | 86 18012345678 | 15812345678

**不匹配：**15412345678 | 12912345678 | 180123456789

#### 校验中国固话号码

**描述：**固话号码，必须加区号（以 0 开头）。
3 位有效区号：010、020~029，固话位数为 8 位。
4 位有效区号：03xx 开头到 09xx，固话位数为 7。

如果想了解更详细的信息，请参考 [**_百度百科-电话区号_**](http://baike.baidu.com/link?url=sX8JoxK1ja5uM5pDYvQe27_QsyqAZ_78DLSeEvwjqtG_uXqU6p5Oh7CPbImNbnwu1ClOmD8udgDIswZfYzQIw0z3BYZO3eTplvVDzieuowTYqt7yHGDAqyT7o4vvGhg4) 。

```
^(010|02[0-9])(\s|-)\d{8}|(0[3-9]\d{2})(\s|-)\d{7}$
```

**匹配：**010-12345678 | 010 12345678 | 0512-1234567 | 0512 1234567

**不匹配：**1234567 | 12345678

#### 校验 IPv4 地址

**描述：**IP 地址是一个 32 位的二进制数，通常被分割为 4 个“8 位二进制数”（也就是 4 个字节）。IP 地址通常用“点分十进制”表示成（a.b.c.d）的形式，其中，a,b,c,d 都是 0~255 之间的十进制整数。

```
^([01]?\d\d?|2[0-4]\d|25[0-5])\.([01]?\d\d?|2[0-4]\d|25[0-5])\.([01]?\d\d?|2[0-4]\d|25[0-5])\.([01]?\d\d?|2[0-4]\d|25[0-5])$
```

**匹配：**0.0.0.0 | 255.255.255.255 | 127.0.0.1

**不匹配：**10.10.10 | 10.10.10.256

#### 校验 IPv6 地址

**描述：**IPv6 的 128 位地址通常写成 8 组，每组为四个十六进制数的形式。

IPv6 地址可以表示为以下形式：

* IPv6 地址
* 零压缩 IPv6 地址([section 2.2 of rfc5952](https://tools.ietf.org/html/rfc5952#section-2.2))
* 带有本地链接区域索引的 IPv6 地址 ([section 11 of rfc4007](https://tools.ietf.org/html/rfc4007#section-11))
* 嵌入 IPv4 的 IPv6 地址([section 2 of rfc6052](https://tools.ietf.org/html/rfc6052#section-2)
* 映射 IPv4 的 IPv6 地址 ([section 2.1 of rfc2765](https://tools.ietf.org/html/rfc2765#section-2.1))
* 翻译 IPv4 的 IPv6 地址 ([section 2.1 of rfc2765](https://tools.ietf.org/html/rfc2765#section-2.1))

显然，IPv6 地址的表示方式很复杂。你也可以参考

[**_百度百科-IPv6_**](http://baike.baidu.com/link?url=D3nmh0q_G_ZVmxXFG79mjjNfT4hs9fwjqUgygh-tvhq43KYqx88HV27WEXmoT4nA4iGzXwXMm5L-j50C2gSL5q)

[**_Stack overflow 上的 IPv6 正则表达高票答案_**](http://stackoverflow.com/questions/53497/regular-expression-that-matches-valid-ipv6-addresses)

```
(([0-9a-fA-F]{1,4}:){7,7}[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,7}:|([0-9a-fA-F]{1,4}:){1,6}:[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,5}(:[0-9a-fA-F]{1,4}){1,2}|([0-9a-fA-F]{1,4}:){1,4}(:[0-9a-fA-F]{1,4}){1,3}|([0-9a-fA-F]{1,4}:){1,3}(:[0-9a-fA-F]{1,4}){1,4}|([0-9a-fA-F]{1,4}:){1,2}(:[0-9a-fA-F]{1,4}){1,5}|[0-9a-fA-F]{1,4}:((:[0-9a-fA-F]{1,4}){1,6})|:((:[0-9a-fA-F]{1,4}){1,7}|:)|fe80:(:[0-9a-fA-F]{0,4}){0,4}%[0-9a-zA-Z]{1,}|::(ffff(:0{1,4}){0,1}:){0,1}((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])|([0-9a-fA-F]{1,4}:){1,4}:((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9]))
```

**匹配：**1:2:3:4:5:6:7:8 | 1:: | 1::8 | 1::6:7:8 | 1::5:6:7:8 | 1::4:5:6:7:8 | 1::3:4:5:6:7:8 | ::2:3:4:5:6:7:8 | 1:2:3:4:5:6:7:: | 1:2:3:4:5:6::8 | 1:2:3:4:5::8 | 1:2:3:4::8 | 1:2:3::8 | 1:2::8 | 1::8 | ::8 | fe80::7:8%1 | ::255.255.255.255 | 2001:db8:3:4::192.0.2.33 | 64:ff9b::192.0.2.33

**不匹配：**1.2.3.4.5.6.7.8 | 1::2::3

### 特定字符

匹配长度为 3 的字符串：`^.{3}$`。

匹配由 26 个英文字母组成的字符串：`^[A-Za-z]+$`。

匹配由 26 个大写英文字母组成的字符串：`^[A-Z]+$`。

匹配由 26 个小写英文字母组成的字符串：`^[a-z]+$`。

匹配由数字和 26 个英文字母组成的字符串：`^[A-Za-z0-9]+$`。

匹配由数字、26 个英文字母或者下划线组成的字符串：`^\w+$`。

### 特定数字

匹配正整数：`^[1-9]\d*$`

匹配负整数：`^-[1-9]\d*$`

匹配整数：`^(-?[1-9]\d*)|0$`

匹配正浮点数：`^[1-9]\d*\.\d+|0\.\d+$`

匹配负浮点数：`^-([1-9]\d*\.\d*|0\.\d*[1-9]\d*)$`

匹配浮点数：`^-?([1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0)$`

## 参考

* [正则应用之——日期正则表达式](http://blog.csdn.net/lxcnn/article/details/4362500)

* [http://www.regexlib.com/](http://www.regexlib.com/)
