package com.grailszipcodes

class ZipCode {
    String postalCode
    String name
    String stateCode
    String state
    static constraints = {
      state()
      stateCode()
      postalCode()
      name()
    }
}
