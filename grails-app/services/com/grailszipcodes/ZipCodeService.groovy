package com.grailszipcodes

import grails.transaction.Transactional

@Transactional
class ZipCodeService {

    def serviceMethod() {

    }

    def loadCodes() {
        def states = ['AL', 'AK', 'AZ', 'AR', 'CA', 'CO', 'CT', 'DE', 'FL', 'GA',
                'HI', 'ID', 'IL', 'IN', 'IA', 'KS', 'KY', 'LA', 'ME', 'MD',
                'MA', 'MI', 'MN', 'MS', 'MO', 'MT', 'NE', 'NV', 'NH', 'NJ',
                'NM', 'NY', 'NC', 'ND', 'OH', 'OK', 'OR', 'PA', 'RI', 'SC',
                'SD', 'TN', 'TX', 'UT', 'VT', 'VA', 'WA', 'WV', 'WI', 'WY']
        states.each { state ->
            withRest(uri: 'http://api.geonames.org') {
                def http = get(path: '/postalCodeSearch', query:[placename:state, country:'US', username:'jholland']) { resp, xml ->
                    xml.code.each {
                        def code = new ZipCode(postalCode:it.postalcode.text(), name:it.name.text(), stateCode:it.adminCode1.text(), state:it.adminName1.text())
                        code.save()
                    }
                }
            }
        }
    }

    def clearCodes() {
        ZipCode.executeUpdate("delete ZipCode")
    }
}
