import java.util.*;
import duke.*;

public class EarthQuakeClient2
{
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] args) {

        quakesWithFilter();
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        return answer;
    }

    public static void quakesWithFilter() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        EarthQuakeClient2 eqc = new EarthQuakeClient2();

//        Filter magF = new MagnitudeFilter(4.0, 5.0);
//        ArrayList<QuakeEntry> magFiltered = eqc.filter(list, magF);
//
//        Filter depthF = new DepthFilter(-35000.0, -12000.0);
//        ArrayList<QuakeEntry> magDepthFiltered = eqc.filter(magFiltered, depthF);
//
//
//        for (QuakeEntry qe: magDepthFiltered) {
//            System.out.println(qe);
//        }
        Location tokyo = new Location(35.42, 139.43);

        Filter distF = new DistanceFilter(tokyo, 10000000);
        ArrayList<QuakeEntry> distFiltered = eqc.filter(list, distF);

        Filter phraseFilter = new PhraseFilter("end", "Japan");
        ArrayList<QuakeEntry> distPhraseFiltered = eqc.filter(distFiltered, phraseFilter);


        for (QuakeEntry qe: distPhraseFiltered)
            System.out.println(qe);
    }
    
    public void testMatchAllFilter() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.0, 2.0));
        maf.addFilter(new DepthFilter(-100000.0, -10000.0));
        maf.addFilter(new PhraseFilter("any", "a"));
        EarthQuakeClient2 eqc = new EarthQuakeClient2();
        ArrayList<QuakeEntry> allFilters = eqc.filter(list,maf);
        
        //if (allFilters)
        for (QuakeEntry qe: allFilters) { 
            System.out.println(qe);
        }
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }
}
