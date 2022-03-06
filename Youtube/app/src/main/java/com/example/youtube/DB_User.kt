package com.example.youtube

class DB_User {
    companion object{
        val arrUsers = arrayListOf<User>()


        init {
            arrUsers
                .add(
                    User("alejja13","aleja13@email.com")
                )
            arrUsers
                .add(
                    User("herziox","herziox@email.com")
                )

        }
    }
}