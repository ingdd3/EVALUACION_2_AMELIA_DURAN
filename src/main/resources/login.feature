Feature: Inicio de sesión
  Como usuario
  Quiero poder iniciar sesión en el sistema

  Scenario: Ingresar nombre de usuario y contraseña correctos
    Given Estoy en la página de inicio de sesión
    When Ingreso el nombre de usuario "johndoe" y la contraseña "secretpassword"
    Then Debería ver el panel de control del usuario

  Scenario: Ingresar nombre de usuario y contraseña incorrectos
    Given Estoy en la página de inicio de sesión
    When Ingreso el nombre de usuario "johndoe" y la contraseña incorrecta "wrongpassword"
    Then Debería ver un mensaje de error de inicio de sesión

  Scenario: Ingresar valores vacíos
    Given Estoy en la página de inicio de sesión
    When No ingreso ningún nombre de usuario ni contraseña
    Then Debería ver un mensaje de error de campos vacíos
