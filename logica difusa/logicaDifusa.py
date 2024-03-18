# Leonardo Daniel Salazar Rodriguez
import numpy as np
import skfuzzy as fuzz
from skfuzzy import control as ctrl
import matplotlib.pyplot as plt

# Variables lingüisticas
calidad_comida = ctrl.Antecedent(np.arange(0, 11, 1), 'calidad_comida')
calidad_servicio = ctrl.Antecedent(np.arange(0, 11, 1), 'calidad_servicio')
tiempo_espera = ctrl.Antecedent(np.arange(0, 61, 1), 'tiempo_espera')  # En minutos
satisfaccion = ctrl.Consequent(np.arange(0, 11, 1), 'satisfaccion')

# Definir los conjuntos difusos y sus funciones de pertenencia
calidad_comida.automf(3, names=['mala', 'regular', 'buena'])
calidad_servicio.automf(3, names=['malo', 'aceptable', 'excelente'])
tiempo_espera['corto'] = fuzz.trimf(tiempo_espera.universe, [0, 0, 30])
tiempo_espera['medio'] = fuzz.trapmf(tiempo_espera.universe, [20, 30, 40, 50])
tiempo_espera['largo'] = fuzz.trimf(tiempo_espera.universe, [40, 60, 60])
satisfaccion['insatisfecho'] = fuzz.sigmf(satisfaccion.universe, 2.5, -1)
satisfaccion['neutro'] = fuzz.gaussmf(satisfaccion.universe, 5, 1.5)
satisfaccion['satisfecho'] = fuzz.sigmf(satisfaccion.universe, 7.5, 1)

# Reglas
regla1 = ctrl.Rule(calidad_comida['buena'] & calidad_servicio['excelente'], satisfaccion['satisfecho'])
regla2 = ctrl.Rule(calidad_comida['buena'] & calidad_servicio['aceptable'], satisfaccion['neutro'])
regla3 = ctrl.Rule(calidad_servicio['excelente'] & tiempo_espera['corto'], satisfaccion['satisfecho'])
regla4 = ctrl.Rule(tiempo_espera['largo'] | calidad_comida['mala'], satisfaccion['insatisfecho'])
regla5 = ctrl.Rule(calidad_servicio['malo'] & tiempo_espera['medio'], satisfaccion['insatisfecho'])
regla6 = ctrl.Rule(calidad_servicio['aceptable'] & tiempo_espera['corto'], satisfaccion['neutro'])
regla7 = ctrl.Rule(calidad_comida['regular'] & calidad_servicio['malo'], satisfaccion['insatisfecho'])
regla8 = ctrl.Rule(calidad_comida['buena'] & tiempo_espera['largo'], satisfaccion['neutro'])
regla9 = ctrl.Rule(calidad_servicio['excelente'] & tiempo_espera['medio'], satisfaccion['neutro'])
regla10 = ctrl.Rule(calidad_comida['mala'] & calidad_servicio['malo'], satisfaccion['insatisfecho'])

sistema_control = ctrl.ControlSystem([regla1, regla2, regla3, regla4, regla5, regla6, regla7, regla8, regla9, regla10])
sistema = ctrl.ControlSystemSimulation(sistema_control)

sistema.input['calidad_comida'] = 8.5
sistema.input['calidad_servicio'] = 7
sistema.input['tiempo_espera'] = 45

sistema.compute()

resultado_satisfaccion = sistema.output['satisfaccion']
print(f"El nivel de satisfacción del cliente es: {resultado_satisfaccion}")

satisfaccion.view(sim=sistema)
plt.show()