package com.grailszipcodes

class CodeManagerController {

    def index() {
        redirect(action: "list", params:params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [codeManagerInstanceList: CodeManager.list(params),
         codeManagerInstanceTotal: CodeManager.count()]
    }
}
