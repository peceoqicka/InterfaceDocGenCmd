package com.peceoqicka.idgcmd.template

import com.google.gson.Gson
import com.peceoqicka.idgcmd.model.DocumentData
import freemarker.template.Configuration
import freemarker.template.Template
import java.io.*
import java.nio.charset.Charset

object TemplateGenerator {
    private lateinit var config: Configuration
    private lateinit var template: Template

    fun init() {
        config = Configuration()
        config.tagSyntax = Configuration.SQUARE_BRACKET_TAG_SYNTAX
        config.defaultEncoding = "UTF-8"
        config.outputEncoding = "UTF-8"
        config.setDirectoryForTemplateLoading(File(TemplateConfig.TEMPLATE_DIR))
    }

    fun output(docData: DocumentData, templateLocation: String = TemplateConfig.DEFAULT_TEMPLATE_FILE,
               outputLocation: String = TemplateConfig.DEFAULT_OUTPUT_LOCATION) {
        template = config.getTemplate(templateLocation, "UTF-8")

        val writer = OutputStreamWriter(FileOutputStream(File(outputLocation)), Charset.forName("UTF-8"))
        val outputData = HashMap<String, Any>()
        outputData.apply {
            put(TemplateConfig.DOCUMENT_NAME, docData.docName)
            put(TemplateConfig.DOCUMENT_VERSION, docData.docVersion)
            put(TemplateConfig.COMPANY, docData.company)
            val catalogSourceStr = Gson().toJson(docData.catalog)
            put(TemplateConfig.CATALOG_SOURCE, catalogSourceStr)
            put(TemplateConfig.UPDATE_LIST, docData.update)
            put(TemplateConfig.CONTENT_LIST, docData.content)
        }
        template.process(outputData, writer)
        writer.flush()
        writer.close()
    }
}