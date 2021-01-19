package com.example.contentproviderpractice

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class MyContactsRepository(private val source: MyContactsDataSource, private val myDispatcher: CoroutineDispatcher) {

    // 다른 예제들은 CursorLoader로 비동기처리하는데 나는 코루틴을 쓸거야
    suspend fun fetchContacts(): List<MyContact> = withContext(myDispatcher) {
        source.fetchContacts()
    }
}