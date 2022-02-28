<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                version="1.0">
    <xsl:output method="html" indent="yes" />
    <xsl:template match="/">
        <html>
            <head>
                <title>Gyms</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
                      integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous"/>

            </head>
            <body>
                <a href="/gyms" style="position: absolute; top: 15px; right: 15px">Back to Thymeleaf version</a>
                <div class="container">
                    <ul class="nav justify-content-center">
                        <li class="nav-item">
                            <a class="nav-link" href="/xslt/gyms">Show gyms</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/xslt/season_passes">Show season passes</a>
                        </li>
                    </ul>
                    <xsl:apply-templates />
                </div>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="ArrayList">
        <table class="table table-striped table-dark table-hover">
            <caption style="caption-side: top">Gyms</caption>
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Address</th>
                    <th>Room number</th>
                    <th>Open time</th>
                    <th>Season passes</th>
                </tr>
            </thead>
            <xsl:for-each select="item">
                <tr>
                    <td>
                        <xsl:value-of select="id" />
                    </td>
                    <td>
                        <xsl:value-of select="address" />
                    </td>
                    <td>
                        <xsl:value-of select="gymNum" />
                    </td>
                    <td>
                        <xsl:value-of select="openTime" />
                    </td>
                    <td>
                        <xsl:value-of select="passes" />
<!--                        <xsl:apply-templates select="passes"/>-->
                    </td>
                </tr>
            </xsl:for-each>
        </table>
    </xsl:template>

    <xsl:template match="passes">
        <table class="table table-striped table-dark table-hover">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Full name</th>
                    <th>Duration Month</th>
                </tr>
            </thead>
            <xsl:for-each select="item">
                <tr>
                    <td>
                        <xsl:value-of select="id" />
                    </td>
                    <td>
                        <xsl:value-of select="fullName" />
                    </td>
                    <td>
                        <xsl:apply-templates select="durationM"/>
                    </td>
                </tr>
            </xsl:for-each>
        </table>
    </xsl:template>
</xsl:stylesheet>