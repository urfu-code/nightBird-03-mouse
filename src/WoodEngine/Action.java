package WoodEngine;
/**
 * Результаты хода персонажа
 */
public enum Action {
	// В результате хода персонаж умирает;
	Dead,
	// Персонажу не удалось совершить ход.
	// Вероятно, по направлению хода была стена.
	Fail,
	// Успешный ход персонажа
	Ok,
	// Успешный ход персонажа, после которого у персонажа добавляется еще одна жизнь
	Life,
	// Имя неверно, либо помер окончательно в результате хода.
	WoodmanNotFound,
	// К успеху пришёл
	Finish
}