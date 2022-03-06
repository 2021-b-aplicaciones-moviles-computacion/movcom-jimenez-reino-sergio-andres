package com.example.youtube

class DB_Video {
    companion object{
        val arrVideos = arrayListOf<Video>()
        val arrComentaries = arrayListOf<Comentary>()
        init {
            arrVideos
                .add(
                    Video(
                        "Como hacer una casita en minecraft",
                        678,
                        "2022-05-12",
                            R.raw.casa_minecraft,
                        "aleja13@email.com",
                        "alejja13",
                        10000,
                        343,
                        "Que wen video xdxd",
                        R.drawable.img_como_hacer_una_casita_en_minecraft,
                        R.drawable.img_user_2
                    )
                )
            arrVideos
                .add(
                    Video(
                        "Como dibujar un dragon",
                        145,
                        "2016-08-24",
                        R.raw.dibujar_dragon,
                        "herziox@email.com",
                        "herziox",
                        526,
                        2,
                        "Viii.....",
                        R.drawable.img_como_dibujar_un_dragon,
                        R.drawable.img_user_1

                    )
                )
            arrVideos
                .add(
                    Video(
                        "Como crear un nft",
                        45897,
                        "2021-11-24",
                        R.raw.crear_nft,
                        "adrian@email.com",
                        "adrianLOL",
                        8478,
                        789,
                        "Esta hecho en paint :v",
                        R.drawable.img_como_crear_un_nft,
                        R.drawable.img_user_3

                    )
                )
            arrVideos
                .add(
                    Video(
                        "Baile de Bully Maguire",
                        1784841,
                        "2015-05-04",
                        R.raw.tobey_maguire,
                        "spiderman@email.com",
                        "el spiderman",
                        56897,
                        78,
                        "El spiderman numero uno +100 lince",
                        R.drawable.img_baile_de_tobey_maguire,
                        R.drawable.img_user_4

                    )
                )
            arrVideos
                .add(
                    Video(
                        "Como ganar dinero con metaversos",
                        7891,
                        "2022-02-09",
                        R.raw.dinero_metaverso,
                        "willyrex@email.com",
                        "willyrex",
                        456,
                        45,
                        "Viii.....",
                        R.drawable.img_como_ganar_dinero_con_metaversos,
                        R.drawable.img_user_5

                    )
                )

        }
    }
}