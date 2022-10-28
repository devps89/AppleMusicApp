package com.example.applemusicapp.view

interface Communicator {
    fun doSearch(term: String, media: String, entity: String, limit: Int)
}