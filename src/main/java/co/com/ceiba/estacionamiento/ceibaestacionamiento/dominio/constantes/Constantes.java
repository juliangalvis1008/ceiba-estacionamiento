package co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.constantes;

public final class Constantes {
	
	private Constantes() {}
	
	public static final String TIPO_VEHICULO_CARRO  = "CARRO";
	public static final String TIPO_VEHICULO_MOTO  = "MOTO";
	
	public static final boolean  ESTADO_ACTIVO  = true;
	public static final boolean  ESTADO_INACTIVO  = false;
	
	public static final int CANTIDAD_MAXIMA_MOTOS = 10;
	public static final int CANTIDAD_MAXIMA_CARROS = 20;
	
	public static final String LETRA_INICIO_PLACA = "A";
	
	public static final double VALOR_HORA_CARRO = 1000;
	public static final double VALOR_HORA_MOTO = 500;
	public static final double VALOR_DIA_CARRO = 8000;
	public static final double VALOR_DIA_MOTO = 4000;
	
	public static final int  TOPE_CILINDRAJE_MOTO = 500;
	
	public static final int TOPE_MIN_HORAS_COBRO_X_DIA = 9;
	public static final int TOPE_MAX_HORAS_COBRO_X_DIA = 24;
	public static final int MINUTOS_X_HORA_PARQUEO = 60;
	
	public static final double  VALOR_ADICIONAL_TOPE_CILINDRAJE_MOTO = 2000;
	
	public static final String MENSAJE_TIPO_VEHICULO_INVALIDO = "El tipo de veh�culo a ingresar corresponde a un tipo v�lido para este estacionamiento";
	public static final String MENSAJE_SOBRECUPO_CARRO = "Actualmente no se cuenta con cupos disponibles para Carros";
	public static final String MENSAJE_SOBRECUPO_MOTOS = "Actualmente no se cuenta con cupos disponibles para Motos";
	public static final String MENSAJE_ACCESO_NO_AUTORIZADO_PLACA = "El vehiculo tiene una placa que no esta habilitada para ingresar al parqueadero el d�a de hoy";
	public static final String VEHICULO_YA_EXISTE_EN_PARQUEADERO = "Este Vehiculo ya se encuentra dentro del parqueadero. Por favor revisar";
	
	public static final String MONEDA = "PESOS";
	public static final int  MILISEGUNDOS_A_MINUTOS = 60000;


	
	
}
