import xml.etree.ElementTree as ET
import pandas as pd
import matplotlib.pyplot as plt

def leer_xml(ruta_xml):
    # Cargar el archivo XML
    tree = ET.parse(ruta_xml)
    root = tree.getroot()

    # Inicializar diccionario para almacenar los pares clave-valor
    datos = {}

    # Iterar sobre los elementos <entry> y extraer las claves y valores
    for entry in root.findall('./tiempos/entry'):
        clave = entry.find('key').text
        valor = int(entry.find('value').text)
        datos[clave] = valor
    
    # Retornar el diccionario de datos
    return datos

# Llamar a la función y pasarle la ruta del archivo XML
datos_xml = leer_xml('../../Tiempos de ejecución/tiempos.xml')

# Convertir el diccionario a DataFrame de Pandas
data_xml = pd.DataFrame(list(datos_xml.items()), columns=['Algoritmo', 'Tiempo'])

# Ordenar los datos por el tiempo en orden descendente
data_xml_sorted = data_xml.sort_values(by="Tiempo", ascending=False)

# Extraer las columnas "Algoritmo" y "Tiempo"
algoritmos_xml = data_xml_sorted["Algoritmo"]
tiempos_xml = data_xml_sorted["Tiempo"]

# Crear la figura y el subgráfico
fig, ax = plt.subplots(figsize=(10, 6))

# Gráfico para datos del XML
bars = ax.barh(algoritmos_xml, tiempos_xml, color='lightgreen')
ax.set_xlabel('Tiempo')
ax.set_ylabel('Algoritmo')
ax.set_title('Tiempo de ejecución de algoritmos')

# Añadir etiquetas a las barras
for bar in bars:
    width = bar.get_width()
    ax.annotate(f'{width}',
                xy=(width, bar.get_y() + bar.get_height() / 2),
                xytext=(3, -8),  # offset vertical y horizontal
                textcoords="offset points",
                ha='left', va='center', fontsize=8)

# Mostrar el gráfico
plt.tight_layout()
plt.show()
