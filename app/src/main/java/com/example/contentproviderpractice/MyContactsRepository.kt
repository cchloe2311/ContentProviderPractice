package com.example.contentproviderpractice

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class MyContactsRepository(private val source: MyContactsDataSource, private val myDispatcher: CoroutineDispatcher) {

    // 별도의 스레드에서 비동기식으로 쿼리를 실행해야 함
    // -> 보통 CursorLoader 클래스를 사용, 여기서는 코루틴 사용
    suspend fun fetchContacts(): List<MyContact> = withContext(myDispatcher) {
        source.fetchContacts()
    }
}