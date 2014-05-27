package view;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import model.board.Cell;
import model.board.Content;
import model.element.*;
import model.kicker.*;

public class ImageManager {

	private Map<String, Image> images = new HashMap<String, Image>();

	public ImageManager() {
		initImages();
	}

	public void initImages() {
		try{		

			images.put(Player.class.getName()+"right1", ImageUtils.loadImage("resources/Player_Right1.png"));
			images.put(Player.class.getName()+"left1", ImageUtils.loadImage("resources/Player_Left1.png"));
			images.put(Player.class.getName()+"up1", ImageUtils.loadImage("resources/Player_Up1.png"));
			images.put(Player.class.getName()+"down1", ImageUtils.loadImage("resources/Player_Down1.png"));
			images.put(Player.class.getName()+"right2", ImageUtils.loadImage("resources/Player_Right2.png"));
			images.put(Player.class.getName()+"left2", ImageUtils.loadImage("resources/Player_Left2.png"));
			images.put(Player.class.getName()+"up2", ImageUtils.loadImage("resources/Player_Up2.png"));
			images.put(Player.class.getName()+"down2", ImageUtils.loadImage("resources/Player_Down2.png"));


			images.put(Cell.class.getName(), ImageUtils.loadImage("resources/Grass.png"));
			images.put(Ball.class.getName(), ImageUtils.loadImage("resources/Ball.png"));
			images.put(Lateral.class.getName(), ImageUtils.loadImage("resources/Lateral.png"));
			images.put(Goal.class.getName(), ImageUtils.loadImage("resources/Goal1.png"));
			//jejeje ahora cargo solo una imagen de un arco
			//images.put(Goal.class.getName()+"2", ImageUtils.loadImage("resources/Goal2.png"));
			images.put(Limit.class.getName(), ImageUtils.loadImage("resources/Limit.png"));
			images.put(Corner.class.getName(), ImageUtils.loadImage("resources/CornerLD.png"));
			
			//y tambien de un solo corner
			//images.put(Corner.class.getName()+"LU", ImageUtils.loadImage("resources/CornerLU.png"));
			//images.put(Corner.class.getName()+"RD", ImageUtils.loadImage("resources/CornerRD.png"));
			//images.put(Corner.class.getName()+"RU", ImageUtils.loadImage("resources/CornerRU.png"));
			images.put(Opponent.class.getName(), ImageUtils.loadImage("resources/Opponent.png"));
			images.put(Out.class.getName(), ImageUtils.loadImage("resources/Out.png"));

		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public Image get(Cell cell) {
		if(cell.hasFixContent()){
			Content fixContent= cell.getFixContent();
			Image fixCont= ImageUtils.overlap(images.get(cell.getClass().getName()), images.get(fixContent.getClass().getName()));
			if(fixContent instanceof Corner){
				Corner corner = (Corner) fixContent;

				Image rotated=rotate(images.get(cell.getFixContent().getClass().getName()),corner.getPos());
				if(cell.hasContent()){
					Content content=cell.getContent();
					if(content instanceof Kicker){
						Kicker kicker= (Kicker) content;
						if(kicker.getNum()==1){
							Image image1 = ImageUtils.overlap(images.get(cell.getClass().getName()), images.get(kicker.getClass().getName()+kicker.getMovement()+"1"));
							return ImageUtils.overlap(rotated, image1);
						}else{
							Image image2 = ImageUtils.overlap(images.get(cell.getClass().getName()), images.get(kicker.getClass().getName()+kicker.getMovement()+"2"));
							return ImageUtils.overlap(rotated, image2);
						}
					}
				}
				return ImageUtils.overlap(images.get(cell.getClass().getName()), rotated);
			}
			
			if(cell.hasContent()){
				Content content=cell.getContent();
				if(content instanceof Kicker){
					Kicker kicker= (Kicker) content;
					if(kicker.getNum()==1){
						Image image1 = ImageUtils.overlap(images.get(cell.getClass().getName()), images.get(kicker.getClass().getName()+kicker.getMovement()+"1"));
						return ImageUtils.overlap(fixCont, image1);
					}else{
						Image image2 = ImageUtils.overlap(images.get(cell.getClass().getName()), images.get(kicker.getClass().getName()+kicker.getMovement()+"2"));
						return ImageUtils.overlap(fixCont, image2);
					}
				}
			}
			return fixCont;
		}
		
		
		if (cell.hasContent()) {
			Content content = cell.getContent();
			if (content instanceof Kicker) {
				Kicker kicker = (Kicker) content;
				if(kicker.getNum()==1){
					Image image1 = ImageUtils.overlap(images.get(cell.getClass().getName()), images.get(kicker.getClass().getName()+kicker.getMovement()+"1"));
					return image1;
				}
				else{
					Image image2 = ImageUtils.overlap(images.get(cell.getClass().getName()), images.get(kicker.getClass().getName()+kicker.getMovement()+"2"));
					return image2;
				}
			} else if(content instanceof Corner){
				Corner corner = (Corner) content;
				Image rotated=rotate(images.get(cell.getContent().getClass().getName()),corner.getPos());
				return ImageUtils.overlap(images.get(cell.getClass().getName()), rotated);
//				if(corner.getPos()==1){
//					Image rotated=rotate(images.get(cell.getContent().getClass().getName()),90);
//					return ImageUtils.overlap(images.get(cell.getClass().getName()), rotated);
//				}
//					//return ImageUtils.overlap(images.get(cell.getClass().getName()), images.get(cell.getContent().getClass().getName()+"LU"));
//				else if(corner.getPos()==2){
//					Image rotated=rotate(images.get(cell.getContent().getClass().getName()),180);
//					return ImageUtils.overlap(images.get(cell.getClass().getName()), rotated);
//				}
//					//return ImageUtils.overlap(images.get(cell.getClass().getName()), images.get(cell.getContent().getClass().getName()+"RU"));
//				else if(corner.getPos()==3)
//					return ImageUtils.overlap(images.get(cell.getClass().getName()), images.get(cell.getContent().getClass().getName()+"LD"));
//				else if(corner.getPos()==4)
//					return ImageUtils.overlap(images.get(cell.getClass().getName()), images.get(cell.getContent().getClass().getName()+"RD"));
			}
			else if(content instanceof Goal){
				Goal goal = (Goal) content;
				Image rotated=rotate(images.get(cell.getContent().getClass().getName()),goal.getNum());
				
				return ImageUtils.overlap(images.get(cell.getClass().getName()), rotated);
//				if(goal.getNum()==1)
//					return ImageUtils.overlap(images.get(cell.getClass().getName()), images.get(cell.getContent().getClass().getName()+"1"));
//				else if(goal.getNum()==2)
//					return ImageUtils.overlap(images.get(cell.getClass().getName()), images.get(cell.getContent().getClass().getName()+"2"));
			}	
			else
				return ImageUtils.overlap(images.get(cell.getClass().getName()), images.get(cell.getContent().getClass().getName()));
		}
		return images.get(cell.getClass().getName());//pasto
	}


	public Image get(String key) {
		return images.get(key);
	}
	
	//http://stackoverflow.com/questions/15927014/rotating-an-image-90-degrees-in-java
	//rotar imagen
	public Image rotate(Image img, double angle)
	{
	    double sin = Math.abs(Math.sin(Math.toRadians(angle))),
	           cos = Math.abs(Math.cos(Math.toRadians(angle)));

	    int w = img.getWidth(null), h = img.getHeight(null);

	    int neww = (int) Math.floor(w*cos + h*sin),
	        newh = (int) Math.floor(h*cos + w*sin);

	    BufferedImage bimg = toBufferedImage(getEmptyImage(neww, newh));
	    Graphics2D g = bimg.createGraphics();

	    g.translate((neww-w)/2, (newh-h)/2);
	    g.rotate(Math.toRadians(angle), w/2, h/2);
	    g.drawRenderedImage(toBufferedImage(img), null);
	    g.dispose();

	    return toImage(bimg);
	}
	
	 public BufferedImage toBufferedImage(Image img){
	        if (img instanceof BufferedImage) {
	            return (BufferedImage) img;
	        }
	        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
	        Graphics2D bGr = bimage.createGraphics();
	        bGr.drawImage(img, 0, 0, null);
	        bGr.dispose();
	        return bimage;
	    }
	 
	 public Image getEmptyImage(int width, int height){
	        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	        return toImage(img);
	    }
	 public Image toImage(BufferedImage bimage){
	        Image img = (Image) bimage;
	        return img;
	    }
	 
}
