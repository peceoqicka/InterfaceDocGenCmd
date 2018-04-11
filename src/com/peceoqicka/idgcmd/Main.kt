package com.peceoqicka.idgcmd

import com.google.gson.Gson
import com.peceoqicka.idgcmd.model.DocumentData
import com.peceoqicka.idgcmd.template.TemplateGenerator
import java.io.FileReader
import java.util.*

fun main(args: Array<String>) {
    println("-----------------接口文档生成器Cmd v1.0-------------------")
    println("                   by. peceoqicka                        ")
    println("                                                         ")
    println("* 接口文档生成根据[配置文件]和[页面模版]生成                ")
    println("* [配置文件]编写规则参看当前目录的文件：example.json        ")
    println("* [配置文件]名称为：cpidoc.json                           ")
    println("* [页面模版]在当前目录下：idg_template.html                ")
    println("* 生成文档默认为： doc.html                                ")
    println()
    println("输入任意字符并回车生成文档：")

    val scanner = Scanner(System.`in`)
    scanner.nextLine()
    scanner.close()

    println("正在组装模版...")
    val docData = Gson().fromJson(FileReader("cpidoc.json").readText(),
            DocumentData::class.java)

    TemplateGenerator.init()
    TemplateGenerator.output(docData)
    println("生成完成！")
}