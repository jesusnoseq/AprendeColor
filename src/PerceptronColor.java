import java.awt.Color;


public class PerceptronColor {
	private Color color;
	private double b,w1,w2,w3,r;
	private double FA;
	private int limit;
	
	public PerceptronColor() {
		 limit=1000;
		 FA=0.00001;
		 b=w1=w2=w3=r=0.0;
	}
	
	public boolean responde(Color c){
		color=c;
		r=w1*(double)color.getRed()+w2*(double)color.getGreen()+w3*(double)color.getBlue()+b;
		return (r>0.0);
	}
	
	public boolean entrena(boolean esperado)
	{
		int respuestaObtenida;
		int respuestaEsperada=BoleanToInt(esperado);
		for(int i=0;i<limit; i++ ){
			respuestaObtenida=BoleanToInt(responde(color));
			if(respuestaObtenida != respuestaEsperada){
				w1=w1+FA*(respuestaEsperada-r)*(double)color.getRed();
				w2=w2+FA*(respuestaEsperada-r)*(double)color.getGreen();
				w3=w3+FA*(respuestaEsperada-r)*(double)color.getBlue();
				//w1=w1+FA*(respuestaEsperada-respuestaObtenida)*(double)color.getRed();
				//w2=w2+FA*(respuestaEsperada-respuestaObtenida)*(double)color.getGreen();
				//w3=w3+FA*(respuestaEsperada-respuestaObtenida)*(double)color.getBlue();
				b=b+FA*(respuestaEsperada-respuestaObtenida);
				
				System.out.println("-------> i:"+i+" b:"+b+"  w1:"+w1+"  w2:"+w2+"  w3:"+w3);
				System.out.println("r:"+r+" rEsp:"+respuestaEsperada+" rObj:"+respuestaObtenida );
			}
			else{
				return true;
			}
		}
		return false;
	}

	private int BoleanToInt(boolean bool){
		return (bool)? 1:0;
	}
	
}
