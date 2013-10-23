package com.grailszipcodes

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(ZipCodeService)
class ZipCodeServiceSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "clearCodes empties the database"() {
        def clearCodes = service.clearCodes()
        if (clearCodes.size  == 0){
            assertTrue()
        }
        fail "Couldn't clear the data"
    }
}
