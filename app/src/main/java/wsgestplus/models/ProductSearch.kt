/**
 * wsGestplusAPI
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: 1.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */
package com.microplus.wsGestplus.models

/**
 * 
 * @param idproduto 
 * @param codigo 
 * @param codbarras 
 * @param codforn 
 * @param nome 
 * @param abreviatura 
 * @param origem 
 * @param visivelsite 
 * @param marca 
 * @param familia 
 * @param idmarca 
 * @param idfamilia 
 * @param preco 
 * @param stock 
 * @param composto 
 * @param obsoleto 
 * @param codigoENome 
 * @param precoPromocao 
 * @param preco3 
 * @param pvp 
 * @param objRegimeIva 
 * @param metodoweb 
 * @param desc 
 * @param minidesc 
 * @param hasImage 
 * @param hasSmallImage 
 * @param novidade 
 * @param promocao 
 * @param validade 
 * @param idcliente 
 * @param defaultarm 
 * @param desccomp 
 * @param compra 
 * @param codigoNomeePreco 
 */
data class ProductSearch (

    val idproduto: kotlin.Int? = null,
    val codigo: kotlin.String? = null,
    val codbarras: kotlin.String? = null,
    val codforn: kotlin.String? = null,
    val nome: kotlin.String? = null,
    val abreviatura: kotlin.String? = null,
    val origem: kotlin.String? = null,
    val visivelsite: kotlin.Boolean? = null,
    val marca: kotlin.String? = null,
    val familia: kotlin.String? = null,
    val idmarca: kotlin.Int? = null,
    val idfamilia: kotlin.Int? = null,
    val preco: kotlin.Double? = null,
    val stock: kotlin.Double? = null,
    val composto: kotlin.Boolean? = null,
    val obsoleto: kotlin.Boolean? = null,
    val codigoENome: kotlin.String? = null,
    val precoPromocao: kotlin.Double? = null,
    val preco3: kotlin.Double? = null,
    val pvp: kotlin.Double? = null,
    val objRegimeIva: Int? = null,
    val metodoweb: kotlin.Int? = null,
    val desc: kotlin.String? = null,
    val minidesc: kotlin.String? = null,
    val hasImage: kotlin.Int? = null,
    val hasSmallImage: kotlin.Int? = null,
    val novidade: kotlin.Boolean? = null,
    val promocao: kotlin.Boolean? = null,
    val validade: java.time.LocalDateTime? = null,
    val idcliente: kotlin.String? = null,
    val defaultarm: kotlin.String? = null,
    val desccomp: kotlin.String? = null,
    val compra: kotlin.Double? = null,
    val codigoNomeePreco: kotlin.Any? = null
) {
}