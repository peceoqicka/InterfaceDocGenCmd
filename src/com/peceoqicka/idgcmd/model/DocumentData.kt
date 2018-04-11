package com.peceoqicka.idgcmd.model

data class DocumentData(var docName: String, var docVersion: String, var company: String,
                        var catalog: List<CatalogItem>, var update: List<UpdateItem>,
                        var content: List<ContentItem>) {

    data class CatalogItem(var text: String, var selectable: Boolean,
                           var interfaceIndex: Int,
                           var nodes: List<CatalogItem>?)

    data class UpdateItem(var version: String, var log: String, var date: String)

    data class ContentItem(var name: String, var requestType: String,
                           var url: String, var param: List<Param>,
                           var response: String, var responseDescribe: List<Param>,
                           var tip: String, var index: Int) {
        data class Param(var name: String, var describe: String)
    }
}