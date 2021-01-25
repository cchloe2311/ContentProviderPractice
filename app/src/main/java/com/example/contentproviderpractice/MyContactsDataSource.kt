package com.example.contentproviderpractice

import android.content.ContentResolver
import android.content.ContentUris
import android.net.Uri
import android.provider.ContactsContract // 기기의 주소록 애플리케이션에서 표시되는 데이터의 출처
import android.util.Log

class MyContactsDataSource(private val contentResolver: ContentResolver) {
    fun fetchContacts(): List<MyContact> {
        val result: MutableList<MyContact> = mutableListOf()

        /**
         * ContentResolver
         * - 이 객체를 사용하여 클라이언트로서 제공자와 통신을 주고받음
         * - ContentProvider 를 구현한 클래스의 인스턴스와 통신
         */
        val cursor = contentResolver.query(
            ContactsContract.Data.CONTENT_URI, // 원하는 데이터를 가져올 주소
            arrayOf(
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID
            ), // 가져올 컬럼의 이름 목록
            null, // where 절
            null, // selection에서 ? 로 표시 곳에 들어갈 데이터
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME // order by
        )
        cursor?.let {
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                result.add(
                    MyContact(
                        cursor.getString(0),
                        cursor.getString(1).toContactImageUri()
                    )
                ) //add the item
                cursor.moveToNext()
            }
            cursor.close()
        }

        return result.toList()
    }

}

fun String.toContactImageUri() = Uri.withAppendedPath(
    ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, this.toLong()),
    ContactsContract.Contacts.Photo.CONTENT_DIRECTORY
).toString()