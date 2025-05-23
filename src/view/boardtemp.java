//package view;
//
//import java.util.ArrayList;
//
//
//
//
//
//import javafx.scene.effect.InnerShadow;
//import javafx.scene.paint.Color;
//import javafx.scene.paint.CycleMethod;
//import javafx.scene.paint.RadialGradient;
//import javafx.scene.shape.Circle;
//import javafx.util.Pair;
//
//public class boardtemp {
//	
//	public static void initializeTrackCells(int startx, int starty, int sk, int r){
//		Circle t = makeCell();
//		
//       	
//       	t.setCenterY(starty);
//       	t.setCenterX(startx);
//       	View.cellAccumulator.add(t);
//       	View.pointToIndex.put(new Pair(startx, starty), View.indexToPoint.size());
//       	View.indexToPoint.add(new Pair(startx, starty));
//       	int sqsk = (int) ((1 / Math.sqrt(2)) * sk);
//           for(int i = 0; i < 8; i++){
//           	Circle x = makeCell();
//           	
//           	starty -= sk;
//           	x.setCenterY(starty);
//           	x.setCenterX(startx);
//           	View.cellAccumulator.add(x);
//           	
//           	View.pointToIndex.put(new Pair(startx, starty), View.indexToPoint.size());
//           	View.indexToPoint.add(new Pair(startx, starty));
//           	
//           }
//          
//           for(int i = 0; i < 5; i++){
//              	Circle x = makeCell();
//
//           	startx -= sqsk;
//              	starty -= sqsk;
//           	x.setCenterY(starty);
//           	x.setCenterX(startx);
//           	View.cellAccumulator.add(x);
//           	View.pointToIndex.put(new Pair(startx, starty), View.indexToPoint.size());
//           	View.indexToPoint.add(new Pair(startx, starty));
//           }
//           for(int i = 0; i < 8; i++){
//              	Circle x = makeCell();
//
//           	startx -= sk;
//              	
//           	x.setCenterY(starty);
//           	x.setCenterX(startx);
//           	View.cellAccumulator.add(x);
//           	View.pointToIndex.put(new Pair(startx, starty), View.indexToPoint.size());
//           	View.indexToPoint.add(new Pair(startx, starty));
//           }
//           for(int i = 0; i < 4; i++){
//              	Circle x = makeCell();
//
//           	starty -= sk;
//              	
//           	x.setCenterY(starty);
//           	x.setCenterX(startx);
//           	View.cellAccumulator.add(x);
//           	View.pointToIndex.put(new Pair(startx, starty), View.indexToPoint.size());
//           	View.indexToPoint.add(new Pair(startx, starty));
//           }
//           for(int i = 0; i < 8; i++){
//              	Circle x = makeCell();
//
//           	startx += sk;
//              	
//           	x.setCenterY(starty);
//           	x.setCenterX(startx);
//           	View.cellAccumulator.add(x);
//           	View.pointToIndex.put(new Pair(startx, starty), View.indexToPoint.size());
//           	View.indexToPoint.add(new Pair(startx, starty));
//           }
//           for(int i = 0; i < 5; i++){
//              	Circle x = makeCell();
//
//           	startx += sqsk;
//              	starty -= sqsk;
//           	x.setCenterY(starty);
//           	x.setCenterX(startx);
//           	View.cellAccumulator.add(x);
//           	View.pointToIndex.put(new Pair(startx, starty), View.indexToPoint.size());
//           	View.indexToPoint.add(new Pair(startx, starty));
//           }
//           for(int i = 0; i < 8; i++){
//              	Circle x = makeCell();
//
//           	starty -= sk;
//              	
//           	x.setCenterY(starty);
//           	x.setCenterX(startx);
//           	View.cellAccumulator.add(x);
//           	View.pointToIndex.put(new Pair(startx, starty), View.indexToPoint.size());
//           	View.indexToPoint.add(new Pair(startx, starty));
//           }
//           for(int i = 0; i < 4; i++){
//              	Circle x = makeCell();
//
//           	startx += sk;
//              	
//           	x.setCenterY(starty);
//           	x.setCenterX(startx);
//           	View.cellAccumulator.add(x);
//           	View.pointToIndex.put(new Pair(startx, starty), View.indexToPoint.size());
//           	View.indexToPoint.add(new Pair(startx, starty));
//           }
//           for(int i = 0; i < 8; i++){
//              	Circle x = makeCell();
//
//           	starty += sk;
//              	
//           	x.setCenterY(starty);
//           	x.setCenterX(startx);
//           	View.cellAccumulator.add(x);
//           	View.pointToIndex.put(new Pair(startx, starty), View.indexToPoint.size());
//           	View.indexToPoint.add(new Pair(startx, starty));
//           }
//           for(int i = 0; i < 5; i++){
//              	Circle x = makeCell();
//
//           	startx += sqsk;
//              	starty += sqsk;
//           	x.setCenterY(starty);
//           	x.setCenterX(startx);
//           	View.cellAccumulator.add(x);
//           	View.pointToIndex.put(new Pair(startx, starty), View.indexToPoint.size());
//           	View.indexToPoint.add(new Pair(startx, starty));
//           }
//           for(int i = 0; i < 8; i++){
//              	Circle x = makeCell();
//
//           	startx += sk;
//              	
//           	x.setCenterY(starty);
//           	x.setCenterX(startx);
//           	View.cellAccumulator.add(x);
//           	View.pointToIndex.put(new Pair(startx, starty), View.indexToPoint.size());
//           	View.indexToPoint.add(new Pair(startx, starty));
//           }
//           for(int i = 0; i < 4; i++){
//              	Circle x = makeCell();
//
//           	starty += sk;
//              	
//           	x.setCenterY(starty);
//           	x.setCenterX(startx);
//           	View.cellAccumulator.add(x);
//           	View.pointToIndex.put(new Pair(startx, starty), View.indexToPoint.size());
//           	View.indexToPoint.add(new Pair(startx, starty));
//           }
//           for(int i = 0; i < 8; i++){
//              	Circle x = makeCell();
//
//           	startx -= sk;
//              	
//           	x.setCenterY(starty);
//           	x.setCenterX(startx);
//           	View.cellAccumulator.add(x);
//           	View.pointToIndex.put(new Pair(startx, starty), View.indexToPoint.size());
//           	View.indexToPoint.add(new Pair(startx, starty));
//           }
//           for(int i = 0; i < 5; i++){
//              	Circle x = makeCell();
//
//           	startx -= sqsk;
//              	starty += sqsk;
//           	x.setCenterY(starty);
//           	x.setCenterX(startx);
//           	View.cellAccumulator.add(x);
//           	View.pointToIndex.put(new Pair(startx, starty), View.indexToPoint.size());
//           	View.indexToPoint.add(new Pair(startx, starty));
//           }
//           for(int i = 0; i < 8; i++){
//              	Circle x = makeCell();
//
//           	starty += sk;
//              	
//           	x.setCenterY(starty);
//           	x.setCenterX(startx);
//           	View.cellAccumulator.add(x);
//           	View.pointToIndex.put(new Pair(startx, starty), View.indexToPoint.size());
//           	View.indexToPoint.add(new Pair(startx, starty));
//           }
//           for(int i = 0; i < 3; i++){
//              	Circle x = makeCell();
//
//           	startx -= sk;
//              	
//           	x.setCenterY(starty);
//           	x.setCenterX(startx);
//           	
//           		View.cellAccumulator.add(x);
//           	View.pointToIndex.put(new Pair(startx, starty), View.indexToPoint.size());
//           	View.indexToPoint.add(new Pair(startx, starty));
//           }
//	}
//	public static void initializeSafeZone(int sk, int r, int w){
//		for(int i = 0; i < 4; i++) View.safeZoneIndexToPoint.add(new ArrayList<>());
//	       for(int pos = 23; pos < 100; pos += 25){
//	       	Pair p = View.indexToPoint.get(pos);
//	       	if(pos == 23){
//	       		int xx = p.x; int yy = p.y;
//	       		for(int j = 1; j <= 4; j++){
//	       			xx += sk;
//	       			View.safeZoneIndexToPoint.get(1).add(new Pair(xx, yy));
//	       			Circle x = new Circle(r );
//	               	x.getStyleClass().add(style);
//
//	       			x.setStroke(View.colOrder.get(1));
//	       			x.setStrokeWidth(w);
//	       			x.setCenterX(xx); x.setCenterY(yy);
//	       			View.cellAccumulator.add(x);
//	       		}
//	       	}
//	       	else if(pos == 23 + 25){
//	       		int xx = p.x; int yy = p.y;
//	       		for(int j = 1; j <= 4; j++){
//	       			yy += sk;
//	       			View.safeZoneIndexToPoint.get(2).add(new Pair(xx, yy));
//	       			Circle x = new Circle(View.cellR );
//	               	x.getStyleClass().add(style);
//
//	       			x.setStroke(View.colOrder.get(2));
//	       			x.setStrokeWidth(w);
//	       			x.setCenterX(xx); x.setCenterY(yy);
//	       			View.cellAccumulator.add(x);
//	       		}
//	       	}
//	       	else if(pos == 23 + 25 + 25){
//	       		int xx = p.x; int yy = p.y;
//	       		for(int j = 1; j <= 4; j++){
//	       			xx -= sk;
//	       			View.safeZoneIndexToPoint.get(3).add(new Pair(xx, yy));
//	       			Circle x = new Circle(View.cellR );
//	               	x.getStyleClass().add(style);
//
//	       			x.setStroke(View.colOrder.get(3));
//	       			x.setStrokeWidth(w);
//	       			x.setCenterX(xx); x.setCenterY(yy);
//	       			View.cellAccumulator.add(x);
//	       		}
//	       	}
//	       	else{
//	       		int xx = p.x; int yy = p.y;
//	       		for(int j = 1; j <= 4; j++){
//	       			yy -= sk;
//	       			View.safeZoneIndexToPoint.get(0).add(new Pair(xx, yy));
//	       			Circle x = new Circle(View.cellR); // example radius
//	               	x.getStyleClass().add(style);
//
//	       			x.setStroke(View.colOrder.get(0));
//	       			x.setStrokeWidth(w);
//	       			x.setCenterX(xx); x.setCenterY(yy);
//	       			View.cellAccumulator.add(x);        	
//	       			}
//	       	}
//	       }
//	       
//	       
//	       
//	}
//	public static void initializeHomecells(int sk, int r, int w){
//		for(int i = 0; i < 4; i++) View.homeindexToPoint.add(new ArrayList<>());
//	       
//		for(int order = 0; order < 4; order++){
//			int x = 0; int dx = 0; int dy = 0;
//	       	if(order == 0){ x = 2; dx = -80; }
//	       	else if(order == 1){ x = 2 + 25; dy = -100; }
//	       	else if(order == 2){ x = 2 + 25 + 25; dx = 80; }
//	       	else {x = 2 + 25 + 25 + 25; dy = 50; }
//	       	
//	       	int xa = View.indexToPoint.get(x).x  + dx; int ya = View.indexToPoint.get(x).y +dy;
//	           Circle cir1 = new Circle(r );
//	           	cir1.getStyleClass().add(style);
//
//	           cir1.setCenterX(xa);
//	           cir1.setCenterY(ya);
//	           cir1.setStroke(View.colOrder.get(order));
//	    		cir1.setStrokeWidth(w);
//	           View.homeindexToPoint.get(order).add(new Pair(xa, ya));
//	           View.cellAccumulator.add(cir1);
//	           
//	           ya += 2 * sk;
//	           cir1 = new Circle(View.cellR );
//	           cir1.getStyleClass().add(style);
//	           cir1.setCenterX(xa);
//	           cir1.setCenterY(ya);
//	           cir1.setStroke(View.colOrder.get(order));
//	    		cir1.setStrokeWidth(3);
//	           View.homeindexToPoint.get(order).add(new Pair(xa, ya));
//	           View.cellAccumulator.add(cir1);
//	           
//	           ya -= sk;
//	           xa += sk;
//	           cir1 = new Circle(View.cellR );
//	           cir1.getStyleClass().add(style);
//	           cir1.setCenterX(xa);
//	           cir1.setCenterY(ya);
//	           cir1.setStroke(View.colOrder.get(order));
//	    		cir1.setStrokeWidth(3);
//	           View.homeindexToPoint.get(order).add(new Pair(xa, ya));
//	           View.cellAccumulator.add(cir1);
//	           
//	          
//	           xa -= 2 * sk;
//	           cir1 = new Circle(View.cellR );
//	           cir1.getStyleClass().add(style);
//	           cir1.setCenterX(xa);
//	           cir1.setCenterY(ya);
//	           cir1.setStroke(View.colOrder.get(order));
//	    		cir1.setStrokeWidth(3);
//	           View.homeindexToPoint.get(order).add(new Pair(xa, ya));
//	           View.cellAccumulator.add(cir1);
//		}
//	}
//}