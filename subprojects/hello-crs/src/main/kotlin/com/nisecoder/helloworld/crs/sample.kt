package com.nisecoder.helloworld.crs

import org.redundent.kotlin.xml.xml

fun main() {
    /**
     * [報告事項の提供方法等](https://www.nta.go.jp/taxes/shiraberu/kokusai/crs/teikyohouhou.htm)
     * [xml_sample](https://www.nta.go.jp/taxes/shiraberu/kokusai/crs/xml/xml_sample_r02.xml)
     */
    val sampleXml = xml("crs:CRS_OECD") {
        namespace("stf", "urn:oecd:ties:crsstf:v5")
        namespace("crs", "urn:oecd:ties:crs:v2")
        namespace("iso", "urn:oecd:ties:isocrstypes:v1")
        namespace("xsi", "http://www.w3.org/2001/XMLSchema-instance")
        namespace("cfc", "urn:oecd:ties:commontypesfatcacrs:v2")
        attribute("version", "2.0")

        "crs:MessageSpec" {
            "crs:TransmittingCountry" { text("JP") }
            "crs:ReceivingCountry" { text("JP") }
            "crs:MessageType" { text("CRS") }
            "crs:Contact" { text("TARO KOKUZEI 03-3581-4161(extXXXX) National Tax Agency, 3-1-1 Kasumigaseki, Chiyoda-ku, Tokyo, 100-8978, JAPAN") }
            "crs:MessageRefId" { text("JP2020JPCN7000012050002002") }
            "crs:MessageTypeIndic" { text("CRS702") }
            "crs:ReportingPeriod" { text("2020-12-31") }
            "crs:Timestamp" { text("2021-04-02T15:07:34") }
        }
        "crs:CrsBody" {
            "crs:ReportingFI" {
                "crs:ResCountryCod" {}
                "crs:IN" {}
                "crs:Name" {}
                "crs:Address" {
                    "cfc:CountryCode" {}
                    "cfc:AddressFree" {}
                }
                "crs:DocSpec" {
                    "stf:DocTypeIndic" {}
                    "stf:DocRefId" {}
                    "stf:CorrDocRefId" {}
                }
            }
            "crs:ReportingGroup" {
                "crs:AccountReport" {
                    "crs:AccountNumber" {}
                    "crs:AccountHolder" {}
                    "crs:AccountBalance" {}
                    "crs:Payment" {}
                }
                "crs:AccountReport" {
                    "crs:AccountNumber" {}
                    "crs:AccountHolder" {}
                    "crs:AccountBalance" {}
                    "crs:Payment" {}
                }
            }

        }

    }

    println(sampleXml)
}
