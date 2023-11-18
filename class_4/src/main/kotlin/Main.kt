import java.text.DecimalFormat//для форматирования чисел
import kotlin.math.sqrt

data class Point(val x: Double, val y: Double)

class Triangle(val vertex1: Point, val vertex2: Point, val vertex3: Point) {

    // Метод для вычисления центра описанной окружности треугольника
    fun circumCircleCenter(): Point {
// Находим длины сторон треугольника
        val a = vertex2.x - vertex1.x
        val b = vertex2.y - vertex1.y
        val c = vertex3.x - vertex1.x
        val d = vertex3.y - vertex1.y

        val e = a * (vertex1.x + vertex2.x) + b * (vertex1.y + vertex2.y)// Проекция на горизонтальную ось
        val f = c * (vertex1.x + vertex3.x) + d * (vertex1.y + vertex3.y)// Проекция на вертикальную ось

        val g = 2.0 * (a * (vertex3.y - vertex2.y) - b * (vertex3.x - vertex2.x)) // Коэффициент при y в формуле центра описанной окружности

// Вычисляем координаты центра окружности
        val centerX = (d * e - b * f) / g
        val centerY = (a * f - c * e) / g

        return Point(centerX, centerY)
    }

    // Метод для вычисления радиуса описанной окружности треугольника
    fun circumCircleRadius(center: Point): Double {
// Вычисляем расстояние от центра окружности до одной из вершин треугольника
        val radius = center.distanceTo(vertex1)

        return radius
    }
}

// Расширяем класс Point новой функцией, которая вычисляет расстояние между двумя точками
fun Point.distanceTo(other: Point): Double {
    val deltaX = this.x - other.x
    val deltaY = this.y - other.y
    return sqrt(deltaX * deltaX + deltaY * deltaY) // Вычисление квадратного корня суммы квадратов разниц по каждой оси
}

// Создаем класс для представления окружности
class Circle(val center: Point, val radius: Double)

fun main() {

// Создаем вершины треугольника
    val vertex1 = Point(3.0, 2.0)
    val vertex2 = Point(4.0, 7.0)
    val vertex3 = Point(5.0, 6.0)

// Создаем треугольник
    val triangle = Triangle(vertex1, vertex2, vertex3)

// Вычисляем центр описанной окружности треугольника
    val circumCircleCenter = triangle.circumCircleCenter()
    val circumCircleRadius = triangle.circumCircleRadius(circumCircleCenter)

// Создаем окружность с центром и радиусом
    val circumCircle = Circle(circumCircleCenter, circumCircleRadius)

// Создаем форматтер для вывода чисел с заданным числом знаков после запятой
    val df = DecimalFormat("#.###")

// Выводим информацию о центре и радиусе окружности
    println("Центр описанной окружности: (${df.format(circumCircle.center.x)}, ${df.format(circumCircle.center.y)})")
    println("Радиус описанной окружности: ${df.format(circumCircle.radius)}")
}

