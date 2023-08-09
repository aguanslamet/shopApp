-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 09 Agu 2023 pada 08.46
-- Versi server: 10.4.25-MariaDB
-- Versi PHP: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `shop_app`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `items`
--

CREATE TABLE `items` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `price` bigint(20) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `items`
--

INSERT INTO `items` (`id`, `description`, `image`, `price`, `title`) VALUES
(1, 'Barang berkualitas KW 1 ', NULL, 10000, 'Gunting'),
(2, 'Barang Bermerek', NULL, 20000, 'Jam Smart'),
(3, 'bisa di pakai untuk sehari-hari', NULL, 15000, 'Buku tulis'),
(4, 'hp keluaran terbaru', NULL, 1100000, 'HP xiomi');

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaction`
--

CREATE TABLE `transaction` (
  `id` bigint(20) NOT NULL,
  `cancel_at` datetime(6) DEFAULT NULL,
  `expired_at` datetime(6) DEFAULT NULL,
  `paid_at` datetime(6) DEFAULT NULL,
  `payment_status` enum('CANCEL','EXPIRED','FAILED','PAID','PENDING') DEFAULT NULL,
  `total_amount` bigint(20) DEFAULT NULL,
  `valid_at` datetime(6) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `transaction`
--

INSERT INTO `transaction` (`id`, `cancel_at`, `expired_at`, `paid_at`, `payment_status`, `total_amount`, `valid_at`, `created_by`) VALUES
(1, NULL, '2023-08-09 13:47:07.000000', '2023-08-09 13:40:00.000000', 'PAID', 20000, NULL, 2),
(2, NULL, '2023-08-09 13:52:14.000000', NULL, 'PENDING', 20000, NULL, 2);

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaction_item_detail`
--

CREATE TABLE `transaction_item_detail` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `base_price` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `product_status` enum('CHECKOUT','TROLI') DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `total_price` bigint(20) DEFAULT NULL,
  `transaction_id` bigint(20) DEFAULT NULL,
  `quantity` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `transaction_item_detail`
--

INSERT INTO `transaction_item_detail` (`id`, `created_by`, `base_price`, `product_id`, `product_status`, `product_name`, `total_price`, `transaction_id`, `quantity`) VALUES
(2, 2, 10000, 1, 'CHECKOUT', 'Gunting', 20000, 1, 2),
(3, 2, 20000, 2, 'TROLI', 'Jam Smart', 20000, NULL, 1),
(4, 2, 20000, 3, 'CHECKOUT', 'Jam Smart', 20000, 2, 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `users`
--

INSERT INTO `users` (`id`, `email`, `password`, `fullname`) VALUES
(1, 'agun@gmail.com', '$2a$10$4GWTkMo1QLD3OiteMAHu/.U7SfkVUt9RV55.GceFFun8OsFFml5iy', 'agun'),
(2, 'agunS10@gmail.com', '$2a$10$8cREkp2fjvnJCMoxNdVApOCvQnLLpERfnXxyOYbBtHYeRRGa.7hny', 'agun10');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `transaction_item_detail`
--
ALTER TABLE `transaction_item_detail`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKq0p8pd4gcxd12hpswjes15fn0` (`transaction_id`);

--
-- Indeks untuk tabel `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `items`
--
ALTER TABLE `items`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT untuk tabel `transaction`
--
ALTER TABLE `transaction`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT untuk tabel `transaction_item_detail`
--
ALTER TABLE `transaction_item_detail`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT untuk tabel `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `transaction_item_detail`
--
ALTER TABLE `transaction_item_detail`
  ADD CONSTRAINT `FKq0p8pd4gcxd12hpswjes15fn0` FOREIGN KEY (`transaction_id`) REFERENCES `transaction` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
