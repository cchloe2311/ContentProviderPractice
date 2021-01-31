# ContentProviderPractice
안드로이드 ContentProvider 컴포넌트 연습 레포지토리입니다 !

## 구현
**#1. MyContactsDataSource**
ContentResolver를 통해 지정한 URI의 데이터를 받아온다(Cursor). 그리고 받아온 행 정보를 사용할 데이터로 조작한다. 참고로 안드로이드 플랫폼에서 제공하는 ContentProvider는 관계형 데이터베이스 모델에 기초해 테이블 집합으로 데이터를 노출하기 때문에 Cursor는 행의 목록을 갖게 된다!
```
val result: MutableList<MyContact> = mutableListOf()

val cursor = contentResolver.query(
    ContactsContract.Data.CONTENT_URI,
    arrayOf(
        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
        ContactsContract.CommonDataKinds.Phone.CONTACT_ID
    ),
    null,
    null,
    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
)

cursor?.let {
    cursor.moveToFirst()
    while (!cursor.isAfterLast) {
        result.add(
            MyContact(
                cursor.getString(0),
                cursor.getString(1).toContactImageUri()
            )
        )
        cursor.moveToNext()
    }
    cursor.close()
}

return result.toList()
```
query() 메소드의 파라미터는 (원하는 데이터를 가져올 주소, 가져울 칼럼의 이름 목록, where 절, selection 에서 ?로 표시될 곳에 들어갈 데이터, order by)로 구성한다.

**#2. MyContactsRepository**
비동기 처리를 위해 코루틴으로 MyContactsDataSource의 메소드 호출
```
suspend fun fetchContacts(): List<MyContact> = withContext(myDispatcher) {
    source.fetchContacts()
}
```
## 추가설명
다음 velog 글을 확인해주세요! [ContentProvider - velog.io/@cchloe2311](https://velog.io/@cchloe2311/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-ContentProvider)
