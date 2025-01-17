package ru.liga.optimalpacking.packages.shared.entities;

public record Parcel(String name, char[][] form, char symbol, int width, int height) {
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('\n');
        sb.append("Название: ").append(name).append('\n');
        sb.append("Форма:\n");
        for (char[] row : form) {
            sb.append(row).append('\n');
        }
        sb.append("Ширина: ").append(width).append('\n');
        sb.append("Высота: ").append(height);
        return sb.toString();
    }
}