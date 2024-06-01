-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 31 Bulan Mei 2024 pada 18.31
-- Versi server: 10.4.28-MariaDB
-- Versi PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `shoesstore`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `casual_shoes`
--

CREATE TABLE `casual_shoes` (
  `shoe_id` int(11) NOT NULL,
  `material` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `running_shoes`
--

CREATE TABLE `running_shoes` (
  `shoe_id` int(11) NOT NULL,
  `weight` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `shoes`
--

CREATE TABLE `shoes` (
  `id` int(11) NOT NULL,
  `brand` varchar(255) NOT NULL,
  `size` int(11) NOT NULL,
  `color` varchar(255) NOT NULL,
  `price` int(11) NOT NULL,
  `stock` int(11) NOT NULL,
  `type` enum('Running','Sport','Casual') NOT NULL,
  `weight` double DEFAULT NULL,
  `sportType` varchar(50) DEFAULT NULL,
  `material` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `shoes`
--

INSERT INTO `shoes` (`id`, `brand`, `size`, `color`, `price`, `stock`, `type`, `weight`, `sportType`, `material`) VALUES
(8, 'Nike', 42, 'Black', 120, 10, 'Running', 250, NULL, NULL),
(9, 'Adidas', 40, 'White', 100, 15, 'Running', 230, NULL, NULL),
(10, 'Puma', 41, 'Blue', 90, 12, 'Sport', NULL, 'Basketball', NULL),
(12, 'Converse', 39, 'Black', 60, 20, 'Casual', NULL, NULL, 'Canvas'),
(13, 'Vans', 40, 'White', 70, 25, 'Casual', NULL, NULL, 'Leather'),
(14, 'New Balance', 44, 'Grey', 130, 10, 'Running', 270, NULL, NULL),
(15, 'Under Armour', 42, 'Green', 110, 5, 'Running', 220, NULL, NULL),
(16, 'Asics', 41, 'Yellow', 95, 7, 'Sport', NULL, 'Running', NULL),
(17, 'Fila', 39, 'Blue', 80, 14, 'Sport', NULL, 'Tennis', NULL),
(18, 'Sketchers', 40, 'Brown', 75, 30, 'Casual', NULL, NULL, 'Suede'),
(19, 'Swallpw', 45, 'Green', 120, 10, 'Casual', NULL, NULL, 'Leather'),
(20, 'Nike', 54, 'Pink', 750000, 10, 'Running', 250, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `sport_shoes`
--

CREATE TABLE `sport_shoes` (
  `shoe_id` int(11) NOT NULL,
  `sportType` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `users`
--

INSERT INTO `users` (`user_id`, `username`, `password`) VALUES
(1, 'arkananta', 'arkananta123'),
(2, 'arkananta', 'arka123');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `casual_shoes`
--
ALTER TABLE `casual_shoes`
  ADD PRIMARY KEY (`shoe_id`);

--
-- Indeks untuk tabel `running_shoes`
--
ALTER TABLE `running_shoes`
  ADD PRIMARY KEY (`shoe_id`);

--
-- Indeks untuk tabel `shoes`
--
ALTER TABLE `shoes`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `sport_shoes`
--
ALTER TABLE `sport_shoes`
  ADD PRIMARY KEY (`shoe_id`);

--
-- Indeks untuk tabel `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `shoes`
--
ALTER TABLE `shoes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT untuk tabel `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `casual_shoes`
--
ALTER TABLE `casual_shoes`
  ADD CONSTRAINT `casual_shoes_ibfk_1` FOREIGN KEY (`shoe_id`) REFERENCES `shoes` (`id`) ON DELETE CASCADE;

--
-- Ketidakleluasaan untuk tabel `running_shoes`
--
ALTER TABLE `running_shoes`
  ADD CONSTRAINT `running_shoes_ibfk_1` FOREIGN KEY (`shoe_id`) REFERENCES `shoes` (`id`) ON DELETE CASCADE;

--
-- Ketidakleluasaan untuk tabel `sport_shoes`
--
ALTER TABLE `sport_shoes`
  ADD CONSTRAINT `sport_shoes_ibfk_1` FOREIGN KEY (`shoe_id`) REFERENCES `shoes` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
