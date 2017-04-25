package uqac.gestionvieetu.Sorties;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import uqac.gestionvieetu.R;

/**
 * Created by guill on 23/04/2017.
 */

public class FragmentMapActivity extends android.support.v4.app.Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnMarkerDragListener {

    private View rootView;
    MapView mapView;
    GoogleMap map;
    private List<LatLng> listOfPoints=new ArrayList<LatLng>();
    private MarkerOptions options = new MarkerOptions();

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.layout_carte, container, false);
        MapsInitializer.initialize(this.getActivity());
        mapView = (MapView) rootView.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(this);
        return rootView;

    }

    @Override
    public void onMapReady(final GoogleMap map)
    {
        LatLng mnPoint = new LatLng(48.419898, -71.052187);
        if (ContextCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            map.setMyLocationEnabled(true);
        }


        float zoomLevel = 16; //This goes up to 21
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(mnPoint, zoomLevel));
        map.addMarker(new MarkerOptions()
                .position(mnPoint)
                .title("Université du Québec à Chicoutimi"));

        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
                Marker nMarker = map.addMarker(new MarkerOptions()
                        .position(point)
                        .title("Nouveau point")
                        .icon(BitmapDescriptorFactory.defaultMarker(new Random().nextInt(360)))
                        .draggable(true)
                );

                listOfPoints.add(point);

                Geocoder geo = new Geocoder(getContext());
                List<Address> addresses = null;
                try {
                    addresses = geo.getFromLocation(point.latitude, point.longitude, 1);
                    if (addresses.isEmpty()) {
                        nMarker.setTitle("Waiting for Location");
                    }
                    else {
                        if (addresses.size() > 0) {
                            nMarker.setTitle(/*addresses.get(0).getLocality() + " "+ */addresses.get(0).getAddressLine(0) +" "  + addresses.get(0).getPostalCode() +" " + addresses.get(0).getAdminArea() + ", " + addresses.get(0).getCountryName());
                            Toast.makeText(getContext(), "Marqueur ajouté : "+ addresses.get(0).getAddressLine(0) +" "  + addresses.get(0).getPostalCode() +" " + addresses.get(0).getAdminArea() + ", " + addresses.get(0).getCountryName(), Toast.LENGTH_LONG).show();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

               // map.setInfoWindowAdapter(nMarker);
                nMarker.showInfoWindow();
            }
        });
       /* map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()
        {
            @Override
            public boolean onMarkerClick(Marker marker) {
                marker.remove();
                return true;
            }

        });*/
        map.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {

            @Override
            public void onMarkerDragStart(Marker marker) {
                marker.remove();
            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                marker.remove();

            }

            @Override
            public void onMarkerDrag(Marker marker) {
                marker.remove();
            }
        });
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();

        listOfPoints.clear();
        try {
            FileInputStream input = this.getContext().openFileInput("latlngpoints.txt");
            DataInputStream din = new DataInputStream(input);
            int sz = din.readInt(); // Read line count
            for (int i = 0; i < sz; i++) {
                String str = din.readUTF();

                String[] stringArray = str.split(",");
                double latitude = Double.parseDouble(stringArray[0]);
                double longitude = Double.parseDouble(stringArray[1]);
                listOfPoints.add(new LatLng(latitude, longitude));

            }
            din.close();
            for (LatLng point : listOfPoints) {
                map.addMarker(new MarkerOptions()
                        /*.position(new LatLng(47, -71.052187))
                        .title("Nouveau point")
                        .icon(BitmapDescriptorFactory.defaultMarker(new Random().nextInt(360)))
                        .draggable(true)*/);
                Toast.makeText(getContext(),"latitude : "+point.latitude+" Longitude : "+point.longitude,Toast.LENGTH_LONG).show();
            }
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public void onPause() {
        try {
            // Modes: MODE_PRIVATE, MODE_WORLD_READABLE, MODE_WORLD_WRITABLE
            FileOutputStream output = this.getContext().openFileOutput("latlngpoints.txt",
                    Context.MODE_PRIVATE);
            DataOutputStream dout = new DataOutputStream(output);
            dout.writeInt(listOfPoints.size()); // Save line count
            for (LatLng point : listOfPoints) {
                dout.writeUTF(point.latitude + "," + point.longitude);
                Toast.makeText(getContext(),point.latitude + "," + point.longitude,Toast.LENGTH_LONG).show();
            }
            dout.flush(); // Flush stream ...
            dout.close(); // ... and close.
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        marker.remove();
        return true;
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {

    }
}
