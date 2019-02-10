package co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio;

public class Constantes {
	
	private Constantes() {
		
	}
	
	public static final String TIPO_VEHICULO_CARRO  = "CARRO";
	public static final String TIPO_VEHICULO_MOTO  = "MOTO";
	
	public static final boolean  ESTADO_ACTIVO  = true;
	public static final boolean  ESTADO_INACTIVO  = false;
	
	public static final int CANTIDAD_MAXIMA_MOTOS = 10;
	public static final int CANTIDAD_MAXIMA_CARROS = 20;
	
	public static final String LETRA_INICIO_PLACA = "A";
	
	public static final double VALOR_HORA_CARRO = 1000;
	public static final double VALOR_HORA_MOTO = 1000;
	public static final double VALOR_DIA_CARRO = 8000;
	public static final double VALOR_DIA_MOTO = 1000;
	
	public static final int  TOPE_CILINDRAJE_MOTO = 500;
	
	public static final int TOPE_MIN_HORAS_COBRO_X_DIA = 9;
	public static final int TOPE_MAX_HORAS_COBRO_X_DIA = 24;
	public static final int MINUTOS_X_HORA_PARQUEO = 24;
	
	public static final double  VALOR_ADICIONAL_TOPE_CILINDRAJE_MOTO = 2000;
	
	public static final String MENSAJE_TIPO_VEHICULO_INVALIDO = "El tipo de vehículo a ingresar corresponde a un tipo válido para este estacionamiento";
	public static final String MENSAJE_SOBRECUPO_CARRO = "Actualmente no se cuenta con cupos disponibles para Carros";
	public static final String MENSAJE_SOBRECUPO_MOTOS = "Actualmente no se cuenta con cupos disponibles para Motos";
	public static final String MENSAJE_ACCESO_NO_AUTORIZADO_PLACA = "El vehiculo tiene una placa que no esta habilitada para ingresar al parqueadero el día de hoy";

	public static final String MONEDA = "PESOS";
	
	public static final String NUMERO_PLACA_MOTO = "CMO48C";	
	public static final String NUMERO_PLACA_CARRO = "VHC085";
	public static final String NUMERO_PLACA_INICIAL_NO_VALIDA = "AYC088";
	public static final int CILINDRAJE_MOTO_MENOR_TOPE = 200;
	public static final int CILINDRAJE_MOTO_MAYOR_TOPE = 650;
	public static final String TIPO_VEHICULO_INCORRECTO  = "BICICLETA";
	public static final int CANTIDAD_CARROS_IGUAL_AL_TOPE = 20;
	public static final int CANTIDAD_CARROS_MENOR_AL_TOPE = 12;
	public static final int CANTIDAD_MOTOS_IGUAL_AL_TOPE = 10;
	public static final int CANTIDAD_MOTOS_MENOR_AL_TOPE = 6;
	
	
	
}
