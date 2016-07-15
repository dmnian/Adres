# Adres
Program na podstawie współrzędnych znajduje adres i umieszcza go w bazie danych.

Przykładowy sposób wywołania z poziomu konsoli:

java -jar adresy.jar 5 import.csv

java -jar adresy.jar 1 coordinates.csv

gdzie:

- 5 lub 1 to ilosc watkow przetwarzajacych wspolrzedne
- import.csv lub coordinates.csv to plik z ktorego pobierane sa wspolrzedne

Dane zostaja zapisane do bazy SQLite umieszczonej w katalogu wywołania.
