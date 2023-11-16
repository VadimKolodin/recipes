<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" omit-xml-declaration="yes"/>
    <xsl:template match="/">
        <html>
            <body>
                <div align="center">
                    <h1>Рецепты</h1>
                    <ul>
                        <xsl:apply-templates/>
                    </ul>
                </div>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="recipe">
        <li>
            <xsl:element name="a">
                <xsl:attribute name="href">recipes/<xsl:value-of select="@id"/>
                </xsl:attribute>
                <xsl:value-of select="@name"/>
            </xsl:element>
        </li>
    </xsl:template>
</xsl:stylesheet>