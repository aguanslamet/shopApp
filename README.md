# shopApp

JWT + MYSQL

Update 21/06/04
- penambahan fitur rabbitMq
- pada proses order akan kirim data ke rabbit mq masuk ke queue Order kemudian dari akan di consume oleh paymentApps sehingga data ordernya masuk. kemudian dari apps payment akan melakukan pembayaran di sini akan mengirim data ke Result Queue yang akan menjadi callback dari proses pembayaran
