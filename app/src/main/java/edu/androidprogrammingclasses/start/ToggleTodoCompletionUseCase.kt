package edu.androidprogrammingclasses.start

class ToggleTodoCompletionUseCase(
  private val todosListRepository: TodosListRepository
) {

  fun invoke(todoId: Int) {
    val todoToModify = todosListRepository.getTodoById(todoId)

    val modifiedTodo = todoToModify.run {
      copy(completed = completed.not())
    }

    todosListRepository.updateTodo(modifiedTodo)
  }
}
