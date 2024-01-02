package com.example.myapplication.ui.location;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.database.graph_map.GraphDatabase;
import com.example.myapplication.database.graph_map.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {

    private List<Node> locationList;
    private Context context;
    private LocationViewModel viewModel;
    private LifecycleOwner lifecycleOwner;

    private Map<Integer, Integer> distanceFromSource = new HashMap<>();

    private Map<Integer, Integer> previousNodes = new HashMap<>();

    private GraphDatabase graphDatabase;

    public LocationAdapter(Context context, LocationViewModel viewModel, LifecycleOwner lifecycleOwner) {
        this.context = context;
        this.viewModel = viewModel;
        this.lifecycleOwner = lifecycleOwner;

        graphDatabase = GraphDatabase.getInstance();
        previousNodes = graphDatabase.getPreviousNodes();

        // Observe changes in location list and update UI accordingly
        viewModel.getLocationListLiveData().observe(lifecycleOwner, nodes -> {
            setLocationList(nodes);
        });

        // Observe changes in routeLiveData and update UI accordingly
        viewModel.getRouteLiveData().observe(lifecycleOwner, routeLocations -> {
            notifyDataSetChanged();
        });
    }
    private List<Node> getPath(int dstId){
        List<Node> path = new ArrayList<>();

        // Retrieve the path from the previousNodes map
        int currentId = dstId;
        while (currentId != Integer.MIN_VALUE) {
            Node currentNode = graphDatabase.getNodes().get(currentId);
            path.add(currentNode);
            currentId = previousNodes.get(currentId);
        }

        // Reverse the path to maintain the correct order
        Collections.reverse(path);

        return path;
    }

    public void setLocationList(List<Node> locationList) {
        this.locationList = locationList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_location, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Node location = locationList.get(position);

        holder.cinemaNameTextView.setText(location.getLabel());

        LiveData<Map<Integer, Integer>> distancesLiveData = viewModel.getShortestDistancesLiveData();
        distancesLiveData.observe(lifecycleOwner, shortestDistances -> {
            int distance = shortestDistances.getOrDefault(location.getId(), 0);
            if (distance == Integer.MAX_VALUE) distance = 0;
            String distanceText = String.valueOf(distance);
            holder.distanceTextView.setText(distanceText);
        });

        // Display the complete route in the TextView
        holder.routeLocationsTextView.setText(formatPathString(getPath(location.getId())));
    }

    private String formatPathString(List<Node> path) {
        if (path != null && path.size() > 1) {
            StringBuilder pathStringBuilder = new StringBuilder("Shortest Path: ");
            for (int i = 0; i < path.size(); i++) {
                Node routeNode = path.get(i);
                pathStringBuilder.append(routeNode.getLabel());
                if (i < path.size() - 1) {
                    pathStringBuilder.append(" -> ");
                }
            }
            return pathStringBuilder.toString();
        } else {
            return "Shortest Path: No path available";
        }
    }

    @Override
    public int getItemCount() {
        return locationList != null ? locationList.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView cinemaNameTextView;
        TextView distanceTextView;
        TextView routeLocationsTextView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            cinemaNameTextView = itemView.findViewById(R.id.cinemaName);
            distanceTextView = itemView.findViewById(R.id.distance_from_source);
            routeLocationsTextView = itemView.findViewById(R.id.routeLocations);
        }
    }
}
