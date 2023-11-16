<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" omit-xml-declaration="yes"/>
    <xsl:template match="/">
        <html>
            <body>
                <xsl:apply-templates/>
                <br/>
                <a href="/xslt/recipes">Назад</a>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="recipe">
        <h1>
            <xsl:value-of select="@name"/>
        </h1>
        <p>
            <xsl:value-of select="@description"/>
        </p>
        <h3>Ингредиенты</h3>
        <ul>
            <xsl:for-each select="ingredients">
                <li>
                    <xsl:value-of select="@name"/>, <xsl:value-of select="@quantity"/>  <xsl:value-of select="@measure"/>
                </li>
            </xsl:for-each>
        </ul>
    </xsl:template>
</xsl:stylesheet>