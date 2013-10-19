package com.grailszipcodes
import groovyx.net.http.*

class CodeManager {
    def states = ['AL', 'AK', 'AZ', 'AR', 'CA', 'CO', 'CT', 'DE', 'FL', 'GA',
                  'HI', 'ID', 'IL', 'IN', 'IA', 'KS', 'KY', 'LA', 'ME', 'MD',
                  'MA', 'MI', 'MN', 'MS', 'MO', 'MT', 'NE', 'NV', 'NH', 'NJ',
                  'NM', 'NY', 'NC', 'ND', 'OH', 'OK', 'OR', 'PA', 'RI', 'SC',
                  'SD', 'TN', 'TX', 'UT', 'VT', 'VA', 'WA', 'WV', 'WI', 'WY']
    static constraints = {
    }

    def loadCodes(){
      states.each {
        withHttp('http://api.geonames.org/postalCodeSearch') {
        def http = get(query:[placename:it, username:'jholland']) { resp, xml ->
          xml.code.each {
            def code = new ZipCode(postalCode:it.postalcode, name:it.name, stateCode:it.adminCode1, state:it.adminName1)
          }
        }
      }
    }
  }
}
