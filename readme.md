##### 2021.10.22 

上传项目到github,有几个测试数据文件太大,超过了100m,push到github报错,直接删除了那几个文件

```JAVA
git add .
git status
git commit -m '删除大文件'
```

之后执行 `git push -u origin master ` 还是报错,那几个文件还是存在了本地, `.git`文件夹也是很大,保存了删除掉的文件.

重复了几次上面步骤问题依旧

解决办法:删除 `.git` 文件夹,重新 

```java
git init
git remote add origin git@github.com:th66778899/Algorithms4th.git
```

之后再进行 `git add . git push -m '' git push -u origin master ` 没有问题,成功上传项目到github

具体原因之后还需要再学习git使用