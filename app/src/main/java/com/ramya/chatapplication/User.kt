package com.ramya.chatapplication

import org.checkerframework.common.returnsreceiver.qual.This

class User {
    var name: String? = null
    var email: String? = null
    var vid: String? = null

    constructor(){}

    constructor(name: String?, email: String?, vid: String?){
        this.name=name
        this.email=email
        this.vid=vid

    }
}